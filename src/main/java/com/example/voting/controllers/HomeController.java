package com.example.voting.controllers;

import com.example.voting.services.VotingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final VotingService votingService;

    public HomeController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping("/")
    public String voting(Model model) {
        model.addAttribute("voting", votingService.list());
        return "index";
    }
}
