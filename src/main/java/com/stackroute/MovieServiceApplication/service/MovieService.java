package com.stackroute.MovieServiceApplication.service;


import com.stackroute.MovieServiceApplication.domain.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> getAllMovie();
    public List<Movie> deleteMovie(int movieId);
    public Movie saveMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public Movie getMovieById(int movieId);
    public List<Movie> getMovieByTitle(String movieTitle);
}
