package com.satckroute.MovieServiceApplication.service;

import com.satckroute.MovieServiceApplication.domain.Movie;
import com.satckroute.MovieServiceApplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieServiceImpl() {
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int movieId) {
        return movieRepository.findById(movieId).get();
    }

    @Override
    public List<Movie> getMovieByTitle(String movieTitle) {
        return movieRepository.findByTitle(movieTitle);
    }

    @Override
    public List<Movie> deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
        return getAllMovie();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie movie1 = movieRepository.getOne(movie.getMovieId());
        if(movie.getMovieTitle()!=null) movie1.setMovieTitle(movie.getMovieTitle());
        if(movie.getMovieOverview()!= null) movie1.setMovieOverview(movie.getMovieOverview());
        if(movie.getVoteAverage()!= 0.0) movie1.setVoteAverage(movie.getVoteAverage());
        return movieRepository.save(movie);
    }
}
