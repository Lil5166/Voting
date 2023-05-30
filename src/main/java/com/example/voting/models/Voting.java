package com.example.voting.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "voting")
@Data
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Voting {
    private String title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // id опитування
    private Long count;
    private String ownerUsername; // username користувача, який створив опитування
    @OneToMany(mappedBy = "voting")
    private List<Candidate> candidateList;// id претиндентів
}
