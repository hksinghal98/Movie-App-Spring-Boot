package com.stackroute.MovieServiceApplication.domain;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;
    private String movieTitle;
    private float voteAverage;
    private String movieOverview;
    private String movieReleaseDate;

    public Movie(int movieId, String movieTitle, float voteAverage, String movieOverview, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.voteAverage = voteAverage;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
    }

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", voteAverage=" + voteAverage +
                ", movieOverview='" + movieOverview + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                '}';
    }
}
