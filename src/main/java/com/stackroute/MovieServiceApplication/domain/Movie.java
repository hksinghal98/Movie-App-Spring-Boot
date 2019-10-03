package com.stackroute.MovieServiceApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;
    private String movieTitle;
    private float voteAverage;
    private String movieOverview;
    private String movieReleaseDate;

    public Movie(String movieTitle, float voteAverage, String movieOverview, String movieReleaseDate) {
        this.movieTitle = movieTitle;
        this.voteAverage = voteAverage;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
    }
}
