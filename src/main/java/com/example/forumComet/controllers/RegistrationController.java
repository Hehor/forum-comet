package com.example.forumComet.controllers;


import com.example.forumComet.models.RegistrationUser;
import com.example.forumComet.service.EmailConfirmation;
import com.example.forumComet.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
private EmailConfirmation emailConfirmation;


    @PostMapping
    public String registration(@RequestBody RegistrationUser registrationUser){
        System.out.println(registrationUser.getPassword());
return userDetailsService.registerUser(registrationUser);
    }

    @GetMapping("/{token}")
    public String confirmUser(@PathVariable String token){
return emailConfirmation.tokenConfirm(token);
    }
}
