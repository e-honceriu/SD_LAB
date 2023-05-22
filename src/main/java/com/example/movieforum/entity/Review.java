package com.example.movieforum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private AppUser reviewer;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String description;

    private Integer rating;


}
