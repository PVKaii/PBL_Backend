package com.example.pbl_api.service.impl;

import com.example.pbl_api.contants.RandomPassword;
import com.example.pbl_api.entity.User;
import com.example.pbl_api.entity.UserAccount;
import com.example.pbl_api.model.UserAccountModel;
import com.example.pbl_api.model.UserModel;
import com.example.pbl_api.repository.UserAccountRepository;
import com.example.pbl_api.repository.UserRepository;
import com.example.pbl_api.service.IUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserModel saveNewUser(UserModel user,UserAccountModel newAccount) {
        user.setUserAccount(newAccount);
        User newUser = new User(user);
        userRepository.save(newUser);
        return null;
    }


    @Override
    public UserModel editUser(long id,UserModel user) {
        User userEdit= userRepository.findUserById(id);
        userEdit.editUser(user);
        userRepository.save(userEdit);
        return null;
    }

    @Override
    public UserModel findUserById(long id) {
        User result = userRepository.findUserById(id);
        return new UserModel(result);
    }

    public void sendEmail() throws MessagingException, UnsupportedEncodingException {
        String toAddress = "pvkk224@gmail.com";
        String fromAddress = "thepvk224@gmail.com";
        String senderName = "lvkn";
        String subject = "Please verify your registration";
        String content = "content";



        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);

    }
}
