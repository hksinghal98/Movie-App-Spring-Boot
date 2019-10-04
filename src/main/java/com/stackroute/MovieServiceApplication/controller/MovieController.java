package com.stackroute.MovieServiceApplication.controller;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.customException.MovieNotFoundException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieController() {
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully Saved", HttpStatus.CREATED);
        }catch (MovieAlreadyExistException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movies")
    public ResponseEntity<?> getAllMovie(){
        ResponseEntity responseEntity;
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getAllMovie(), HttpStatus.OK);
            return responseEntity;
    }
    @GetMapping("movie/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") int movieId){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Movie>(movieService.getMovieById(movieId),HttpStatus.OK);
        }catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.deleteMovie(movieId), HttpStatus.NO_CONTENT);
        }catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PatchMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
      ResponseEntity responseEntity;
        try {
            movieService.updateMovie(movie);
            responseEntity = new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);
        }catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

      return responseEntity;
    }

    @GetMapping("movieByName/{movieTitle}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable("movieTitle") String movieTitle) {
        ResponseEntity responseEntity;
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getMovieByTitle(movieTitle), HttpStatus.OK);
        return responseEntity;
    }

}
