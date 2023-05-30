package com.example.voting.services;

import com.example.voting.models.Candidate;
import com.example.voting.models.Voting;
import com.example.voting.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateService {
    private final Map<Long, List<Candidate>> candidatesByVotingId;
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.candidatesByVotingId = new HashMap<>();
    }

    public void addCandidateToVoting(Voting voting, Candidate candidate) {
        Long votingId = voting.getId();
        if (!candidatesByVotingId.containsKey(votingId)) {
            candidatesByVotingId.put(votingId, new ArrayList<>());
        }

        List<Candidate> candidates = candidatesByVotingId.get(votingId);
        candidates.add(candidate);
        candidatesByVotingId.put(votingId, candidates);
    }

    public List<Candidate> getCandidatesByVotingId(Long votingId) {
        return candidatesByVotingId.getOrDefault(votingId, Collections.emptyList());
    }
    public List<Candidate> getCandidatesByVoting(Voting voting) {
        return candidateRepository.findByVoting(voting);
    }

    public void createCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public void changeCandidateActivity(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        candidate.setActive(true);
        candidateRepository.save(candidate);
    }
}

