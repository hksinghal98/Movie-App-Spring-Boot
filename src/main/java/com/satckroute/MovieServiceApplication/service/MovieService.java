package com.satckroute.MovieServiceApplication.service;


import com.satckroute.MovieServiceApplication.domain.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> getAllMovie();
    public List<Movie> deleteMovie(int movieId);
    public Movie saveMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public Movie getMovieById(int movieId);
}
