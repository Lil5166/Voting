package com.example.voting.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "vote")
@NoArgsConstructor
public class Vote {
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
