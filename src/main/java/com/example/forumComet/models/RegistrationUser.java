package com.example.forumComet.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationUser {

private String username;
private String password;
private String email;
}
