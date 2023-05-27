package com.example.voting.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterFormResponse {
    private String username;
    private String password;
    private String confirmPassword;
}
