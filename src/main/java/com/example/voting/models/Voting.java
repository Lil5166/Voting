package com.example.voting.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "voting")
@Getter
@Data
@Setter
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
    private Long ownerId; // id користувача, який створив опитування
    private Long applicantId;// id претиндента

}
