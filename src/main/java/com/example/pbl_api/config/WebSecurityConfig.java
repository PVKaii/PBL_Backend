package com.example.pbl_api.config;

import com.example.pbl_api.config.CustomAccessDeniedHandler;
import com.example.pbl_api.config.JwtAuthenticationFilter;
import com.example.pbl_api.config.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
//		 securedEnabled = true
//		 jsr250Enabled = true
		prePostEnabled = true
		 )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {

		return new JwtAuthenticationFilter();

	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();

	}
	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(10);

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {

//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//		corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000/"));
//		corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
//		corsConfiguration.setAllowCredentials(true);
//
//		http.csrf().ignoringAntMatchers("/**");
		http.cors().and().csrf().disable();

		http.authorizeRequests()

				.antMatchers("/",

						"/login","/product","/product/{id}")
				.permitAll()

				.anyRequest().authenticated()

				.and().csrf().disable()

				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

		http.sessionManagement()

				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		http.cors().configurationSource(request -> corsConfiguration);


//		http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
//				.authenticated().and().csrf().disable().cors().configurationSource(request -> corsConfiguration);//		corsConfiguration.setExposedHeaders(List.of("Authorization"));

		// You can customize the following part based on your project, it's only a sample
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(Arrays.asList("http://localhost:3000","https://pbl6.000webhostapp.com/"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

}
