package com.example.voting.repository;

import com.example.voting.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findById(Long id);
    Applicant findByName(String name);
}
