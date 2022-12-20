package com.example.pbl_api.config;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;

@Component
public class CustomAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
//        for (Cookie cookie:
//             request.getCookies()) {
//            System.out.println(cookie.getValue());
//        }

        OAuth2AuthorizationRequest rs =  OAuth2AuthorizationRequest.class.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(request.getCookies()[0].getValue())));
        return rs;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        String value = Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(authorizationRequest));
        Cookie cookie = new Cookie("authorize",value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(180);
        response.addCookie(cookie);


        Cookie cookie2 = new Cookie("redirect","123");
        cookie2.setPath("/");
        cookie2.setHttpOnly(true);
        cookie2.setMaxAge(180);
        response.addCookie(cookie2);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }
}
