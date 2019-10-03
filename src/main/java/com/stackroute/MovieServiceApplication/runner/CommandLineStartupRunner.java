package com.stackroute.MovieServiceApplication.runner;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {
    @Autowired
    private MovieService movieService;

    public CommandLineStartupRunner(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("invoked on startup");
        Movie movie = new Movie("WARfkhsdk",(float) 7.3,"Action Movie","02/10/2019");
        try {
            movieService.saveMovie(movie);
        } catch (MovieAlreadyExistException e) {
            e.printStackTrace();
        }
    }
}