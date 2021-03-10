package com.example.forumComet.service;

import com.example.forumComet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmation {

    @Autowired
    private UserRepository userRepository;

    public String tokenConfirm(String token){
        if(userRepository.findByToken(token).isPresent()){
            userRepository.confirmById(userRepository.findByToken(token).orElseThrow(()->new IllegalStateException("")).getId());
        }else{
            throw new IllegalStateException("Already confirmed");
        }
        return "Confirmed";
    }
}
