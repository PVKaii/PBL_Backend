package com.example.pbl_api.service.impl;

import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.entity.UserAccount;
import com.example.pbl_api.model.JwtResponse;
import com.example.pbl_api.model.PasswordChangerModel;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.repository.UserAccountRepository;
import com.example.pbl_api.repository.UserRepository;
import com.example.pbl_api.service.IUserSerivce;
import com.example.pbl_api.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserSerivce {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    RandomPassword randomPassword;

    @Autowired
    JwtService jwtService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(username);
        if(userAccount==null) throw new UsernameNotFoundException(username);
        return UserAccountModel.build(userAccount);
    }

    @Override
    public UserModel loadUserDetailByAccoutName(String name) {

        User result = userRepository.findUserByAccountName(name);
        if (result==null) return null;
        return new UserModel(result);
    }

    @Override
    public Collection<? extends GrantedAuthority> loadRolesByAccoutName(String name) {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(name);
        Collection<? extends GrantedAuthority> rs = userAccount.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).toList();
        return rs;
    }


    @Override
    public String saveNewUser(UserModel user,UserAccountModel newAccount) {
        user.setUserAccount(newAccount);
        User newUser = new User(user);
        userRepository.save(newUser);
        return newUser.getUserAccount().getVerifyCode();
    }


    @Override
    public UserModel editUser(String username,UserModel user) {
        User userEdit= userRepository.findUserByAccountName(username);
        if(userEdit==null) throw new UsernameNotFoundException(username);
        System.out.println(userEdit.getId());
        userEdit.editUser(user);
        userRepository.save(userEdit);
        return null;
    }

    @Override
    public UserModel findUserById(long id) {
        User result = userRepository.findUserById(id);
        return new UserModel(result);
    }

    @Override
    public void emailVerify(String email,String code) throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = "lvkn.pbl6@gmail.com";
        String senderName = "Công ty LVKN";
        String subject = "Please verify your registration";
        String content = "verify account\n <a href='"+ Url.BE_URL +"/verify?code="+code+ "'>verify </a>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);


    }

    @Override
    public JwtResponse login(UserAccountModel user,AuthenticationManager authenticationManager) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=jwtService.generateTokenLogin(authentication);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        UserModel userModel = loadUserDetailByAccoutName(userDetails.getUsername());
        if(userModel.getEnable()==false) throw new UsernameNotFoundException(user.getUsername());
        return new JwtResponse(jwt,userModel,userDetails.getAuthorities());
    }

    @Override
    public void enableAccount(String code) {
        User user = userRepository.findUserByUserAccountVerifyCode(code);
        if(user==null) throw new UsernameNotFoundException(code);
        user.getUserAccount().setEnable(true);
        user.getUserAccount().setVerifyCode(null);
        userRepository.save(user);
    }

    @Override
    public void changePassword(String username, PasswordChangerModel passwordChangerModel,AuthenticationManager authenticationManager) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        passwordChangerModel.getRecentPassword()
                )
        );

        UserAccount account = userAccountRepository.findUserAccountByUsername(username);
        account.setPassword(new BCryptPasswordEncoder().encode(passwordChangerModel.getNewPassword()));
        userAccountRepository.save(account);
    }

    @Override
    public void resetPassword(String username) throws MessagingException, UnsupportedEncodingException {
        String newPassword = randomPassword.Random();
        User user = userRepository.findUserByAccountName(username);
        user.getUserAccount().setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
        String email = user.getEmail();
        String toAddress = email;
        String fromAddress = "lvkn.pbl6@gmail.com";
        String senderName = "Công ty LVKN";
        String subject = "Reset password";
        String content = "your new password is \n <h2>"+newPassword+"</h2>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);
    }

}
