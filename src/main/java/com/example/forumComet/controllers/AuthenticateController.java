package com.example.forumComet.controllers;

import com.example.forumComet.models.AuthRequest;
import com.example.forumComet.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public String generate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (Exception e){
            throw new Exception("invalid username or password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
    @GetMapping("/test")
    public String test(){
        return "Secret key: 1111";
    }
}
