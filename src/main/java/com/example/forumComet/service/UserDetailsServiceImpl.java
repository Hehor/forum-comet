package com.example.forumComet.service;

import com.example.forumComet.models.RegistrationUser;
import com.example.forumComet.models.Role;
import com.example.forumComet.models.User;
import com.example.forumComet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    public String registerUser(RegistrationUser registrationUser){
        boolean usernameCheck = userRepository.findByUsername(registrationUser.getUsername()).isPresent();
        boolean emailCheck = userRepository.findByEmail(registrationUser.getEmail()).isPresent();
        if(usernameCheck || emailCheck){
            throw new IllegalStateException("Username or email already exists");
        }
        String token = UUID.randomUUID().toString();
        User user = new User(registrationUser.getEmail(),registrationUser.getUsername(),
                bCryptPasswordEncoder.encode(registrationUser.getPassword()), Role.USER,token);
        userRepository.save(user);
        String url = "http://localhost:8080/api/v1/registration/" + token;
        emailService.sendMessage(user.getEmail(),"Hello",url);
        //TODO: send email here
        return "Confirm your email please";
    }
}
