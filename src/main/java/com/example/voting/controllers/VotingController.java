package com.example.voting.controllers;

import com.example.voting.models.Candidate;
import com.example.voting.models.Voting;
import com.example.voting.models.VotingResponse;
import com.example.voting.repository.CandidateRepository;
import com.example.voting.repository.UserRepository;
import com.example.voting.repository.VotingRepository;
import com.example.voting.services.CandidateService;
import com.example.voting.services.VotingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;
    private final UserRepository userRepository;
    private final VotingRepository votingRepository;

    private final CandidateRepository candidateRepository;
    private final CandidateService candidateService;


    public VotingController(VotingService votingService, UserRepository userRepository, CandidateService candidateService, VotingRepository votingRepository, CandidateRepository candidateRepository, CandidateService candidateService1) {
        this.votingService = votingService;
        this.userRepository = userRepository;
        this.votingRepository = votingRepository;
        this.candidateRepository = candidateRepository;
        this.candidateService = candidateService1;
    }


    @GetMapping("/add")
    public String showAddVotingPage(Model model) {
        model.addAttribute("newVoting", new Voting());
        return "voting/voting-add";
    }

    @PostMapping("/create")
    public String createVoting(@ModelAttribute("newVoting") VotingResponse votingResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var voting = Voting.builder()
                .title(votingResponse.getTitle())
                .description(votingResponse.getDescription())
                .ownerUsername(username)
                .build();
        votingRepository.save(voting);
        return "redirect:/";
    }

    @GetMapping("/{votingId}")
    public String showVoting(@PathVariable("votingId") Long votingId, Model model) {
        Voting voting = votingService.getVotingById(votingId);
        List<Candidate> candidates = candidateService.getCandidatesByVoting(voting);
        model.addAttribute("voting", voting);
        model.addAttribute("candidates", candidates);
        model.addAttribute("newCandidate", new Candidate());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username.equals(voting.getOwnerUsername())) {
            model.addAttribute("isOwner", true);
        } else {
          model.addAttribute("isOwner", false);
        }


        return "voting/voting-info";
    }

    @PostMapping("/{votingId}/candidates/create")
    public String addCandidateToVoting(
            @PathVariable("votingId") Long votingId,
            @ModelAttribute("newCandidate") Candidate newCandidate
    ) {
        Voting voting = votingService.getVotingById(votingId);
        candidateService.createCandidate(newCandidate);
        Candidate candidate = newCandidate; // Оголошення і ініціалізація змінної candidate
        candidate.setVoting(voting);
        candidate.setActive(false);// Присвоєння votingId кандидату
        candidateRepository.save(candidate); // Збереження кандидата в базі даних
        votingService.saveVoting(voting);
        return "redirect:/voting/{votingId}";
    }

    @PostMapping("/{votingId}/choose/{candidateId}")
    public String chooseCandidate(@PathVariable Long votingId, @PathVariable Long candidateId) {
        candidateService.changeCandidateActivity(candidateId);
        return "redirect:/voting/{votingId}";
    }

}



