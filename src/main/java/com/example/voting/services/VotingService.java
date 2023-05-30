package com.example.voting.services;

import com.example.voting.models.Voting;
import com.example.voting.models.VotingResponse;
import com.example.voting.repository.VotingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {

    private final VotingRepository votingRepository;

    public VotingService(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;
    }
    public void saveVoting(Voting voting) {
        votingRepository.save(voting);
    }
    public Voting getVotingById(Long votingId) {
        return votingRepository.findById(votingId).orElse(null);
    }
    public List<Voting> getAllVotings() {
        return votingRepository.findAll();
    }

}
