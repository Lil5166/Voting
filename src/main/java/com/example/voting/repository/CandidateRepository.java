package com.example.voting.repository;

import com.example.voting.models.Candidate;
import com.example.voting.models.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findById(Long id);
    Candidate findByName(String name);

    @Query("SELECT c FROM Candidate c WHERE c.voting = :voting")
    List<Candidate> findByVoting(@Param("voting") Voting voting);
}
