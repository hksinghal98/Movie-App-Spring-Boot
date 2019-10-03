package com.stackroute.MovieServiceApplication.service;


import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.customException.MovieNotFoundException;
import com.stackroute.MovieServiceApplication.domain.Movie;

import java.util.List;

public interface MovieService {
    public List<Movie> getAllMovie();
    public List<Movie> deleteMovie(int movieId) throws MovieNotFoundException;
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistException;
    public Movie updateMovie(Movie movie) throws MovieNotFoundException;
    public Movie getMovieById(int movieId) throws MovieNotFoundException;
    public List<Movie> getMovieByTitle(String movieTitle);
}
