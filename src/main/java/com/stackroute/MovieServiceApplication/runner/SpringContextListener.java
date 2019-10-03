package com.stackroute.MovieServiceApplication.runner;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MovieService movieService;

    @Value("${movieTitle1}")
    private String movieTitle;
    @Value("${movieOverview}")
    private String movieOverview;
    @Value("${movieReleaseDate}")
    private String movieReleaseDate;
    @Value("${voteAverage}")
    private float voteAverage;

    public SpringContextListener(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("invoked on startup");
        Movie movie = new Movie(movieTitle,voteAverage,movieOverview,movieReleaseDate);
        try {
            movieService.saveMovie(movie);
        } catch (MovieAlreadyExistException e) {
            e.printStackTrace();
        }
    }
}
