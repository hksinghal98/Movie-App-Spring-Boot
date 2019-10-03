package com.stackroute.MovieServiceApplication.runner;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MovieService movieService;

    public SpringContextListener(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("invoked on startup");
        Movie movie = new Movie("WAR",(float) 7.3,"Action Movie","02/10/2019");
        try {
            movieService.saveMovie(movie);
        } catch (MovieAlreadyExistException e) {
            e.printStackTrace();
        }
    }
}