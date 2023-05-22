package com.example.movieforum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"}
        )
})

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate releaseDate;

    private Integer runtime;

    private String description;

    private Integer budget;

    public Movie(String movieString) {
        String[] values = movieString.split("~");
        this.title = values[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.releaseDate = LocalDate.parse(values[1], formatter);
        this.runtime = Integer.valueOf(values[2]);
        this.description = values[3];
        this.budget = Integer.valueOf(values[4]);
    }

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser uploader;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", uploader=" + uploader.getUsername() +
                '}';
    }
}
