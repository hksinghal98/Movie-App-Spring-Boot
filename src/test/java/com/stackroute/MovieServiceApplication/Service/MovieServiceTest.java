package com.stackroute.MovieServiceApplication.Service;

import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.customException.MovieNotFoundException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.repository.MovieRepository;
import com.stackroute.MovieServiceApplication.service.MovieService;
import com.stackroute.MovieServiceApplication.service.MovieServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class MovieServiceTest {

    private Movie movie;

    //Create a mock for MovieRepository
    @Mock
    private MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieTitle("Movie1");
        movie.setMovieReleaseDate("2/12/11");
        movie.setMovieOverview("first");
        movie.setVoteAverage(7.5f);
        movie.setMovieId(1);
        list = new ArrayList<>();
        list.add(movie);

    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistException {
        List<Movie> list = new ArrayList<Movie>();
        list.add(movie);
        when(movieRepository.save((Movie) any())).thenReturn(null);
        when(movieRepository.findByTitleAndDate((String) any(),(String) any())).thenReturn(list);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
    }

    @Test
    public void getAllMovie(){
        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getAllMovie();
        Assert.assertEquals(list,movielist);
    }

    @Test
    public void testGetMovieByIdSuccess() throws MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(true);
        when(movieRepository.findById((Integer) any())).thenReturn(java.util.Optional.ofNullable(movie));
        Movie movie = movieService.getMovieById(1);
        verify(movieRepository,times(1)).existsById(1);
        verify(movieRepository,times(1)).findById(1);
    }

    @Test(expected = MovieNotFoundException.class)
    public void testGetMovieByIdFailure() throws MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(false);
        when(movieRepository.findById((Integer) any())).thenReturn(null);
        Movie movie = movieService.getMovieById(1);

    }

    @Test
    public void deleteMovieTestSuccess() throws MovieAlreadyExistException, MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(true);
        movieRepository.deleteById((Integer)  any());
        List<Movie> list = movieService.deleteMovie(movie.getMovieId());

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).existsById(1);
        verify(movieRepository,times(1)).deleteById(1);

    }

    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(false);
        movieRepository.deleteById((Integer)  any());
        List<Movie> list = movieService.deleteMovie(movie.getMovieId());
    }

    @Test
    public void updateMovieTestSuccess() throws MovieAlreadyExistException, MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(true);
        when(movieRepository.getOne((Integer) any())).thenReturn(movie);
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.updateMovie(movie);
        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);
        verify(movieRepository,times(1)).getOne(1);
        verify(movieRepository,times(1)).existsById(1);

    }

    @Test(expected = MovieNotFoundException.class)
    public void updateMovieTestFailure() throws MovieAlreadyExistException, MovieNotFoundException {
        when(movieRepository.existsById((Integer) any())).thenReturn(false);
        Movie savedMovie = movieService.updateMovie(movie);
        System.out.println("savedMovie" + savedMovie);
    }

}
