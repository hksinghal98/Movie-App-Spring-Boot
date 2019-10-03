package com.satckroute.MovieServiceApplication.controller;

import com.satckroute.MovieServiceApplication.customError.CustomError;
import com.satckroute.MovieServiceApplication.domain.Movie;
import com.satckroute.MovieServiceApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
        }catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movies")
    public ResponseEntity<?> getAllMovie(){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getAllMovie(), HttpStatus.OK);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<CustomError>(new CustomError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
            return responseEntity;
    }
    @GetMapping("movie/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") int movieId){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<Movie>(movieService.getMovieById(movieId),HttpStatus.OK);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<CustomError>(new CustomError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") int movieId){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.deleteMovie(movieId), HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<CustomError>(new CustomError(HttpStatus.BAD_REQUEST, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PatchMapping("movie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
      ResponseEntity responseEntity;
      movieService.updateMovie(movie);
      try{
          responseEntity = new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);
      }catch (Exception ex){
          responseEntity = new ResponseEntity<CustomError>(new CustomError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage()),HttpStatus.NOT_ACCEPTABLE);
      }
      return responseEntity;
    }

    @GetMapping("movieByName/{movieTitle}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable("movieTitle") String movieTitle){
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(movieService.getMovieByTitle(movieTitle),HttpStatus.OK);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<CustomError>(new CustomError(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
