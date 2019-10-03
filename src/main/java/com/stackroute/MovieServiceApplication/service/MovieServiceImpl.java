package com.stackroute.MovieServiceApplication.service;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.customException.MovieNotFoundException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.repository.MovieRepository;
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
    public Movie getMovieById(int movieId) throws MovieNotFoundException{
        if(!movieRepository.existsById(movieId)) throw new MovieNotFoundException("Movie with ID: "+movieId+" doesn't Exist.");
        return movieRepository.findById(movieId).get();
    }

    @Override
    public List<Movie> getMovieByTitle(String movieTitle){
        return movieRepository.findByTitle(movieTitle);
    }

    @Override
    public List<Movie> deleteMovie(int movieId) throws MovieNotFoundException {
        if(!movieRepository.existsById(movieId)) throw new MovieNotFoundException("Movie with ID: "+movieId+" doesn't Exist.");
        movieRepository.deleteById(movieId);
        return getAllMovie();
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistException {
        if(!movieRepository.findByTitleAndDate(movie.getMovieTitle(),movie.getMovieReleaseDate()).isEmpty()) throw new MovieAlreadyExistException("Movie with ID: "+movie.getMovieId()+" Already Exist.");
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieNotFoundException {
        if(!movieRepository.existsById(movie.getMovieId())) throw new MovieNotFoundException("Movie with ID: "+movie.getMovieId()+" doesn't Exist.");
        Movie movie1 = movieRepository.getOne(movie.getMovieId());
        if(movie.getMovieTitle()!=null) movie1.setMovieTitle(movie.getMovieTitle());
        if(movie.getMovieOverview()!= null) movie1.setMovieOverview(movie.getMovieOverview());
        if(movie.getVoteAverage()!= 0.0) movie1.setVoteAverage(movie.getVoteAverage());
        return movieRepository.save(movie);
    }
}
