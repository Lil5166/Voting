package com.example.voting.controllers;

import com.example.voting.models.RegisterFormResponse;
import com.example.voting.models.Role;
import com.example.voting.models.User;
import com.example.voting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RegisterFormResponse registerFormResponse;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserRepository userRepository, RegisterFormResponse registerFormResponse) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.registerFormResponse = registerFormResponse;
    }

    @GetMapping("/login")
    public String login(){
        return "registration&login/login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("registerFormResponse", registerFormResponse);
        return "registration&login/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute RegisterFormResponse registerFormResponse, Model model) {
        var user = User.builder()
                .username(registerFormResponse.getUsername())
                .password(passwordEncoder.encode(registerFormResponse.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return "redirect:/login";
    }
}
