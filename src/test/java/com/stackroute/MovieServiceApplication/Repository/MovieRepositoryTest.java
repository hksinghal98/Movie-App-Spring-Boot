package com.stackroute.MovieServiceApplication.Repository;

import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    private Movie movie;

    @Before
    public void setUp(){
        movie = new Movie();
        movie.setMovieTitle("Movie1");
        movie.setVoteAverage(5.6f);
        movie.setMovieOverview("Testing movie");
        movie.setMovieReleaseDate("23/23/12");
    }

    @After
    public void tearDown(){
        movieRepository.deleteAll();
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        System.out.println("MovieID: "+fetchMovie.getMovieId());
        Assert.assertEquals("Movie1",fetchMovie.getMovieTitle());
        Assert.assertEquals(5.6f,fetchMovie.getVoteAverage(),0.01);
    }

    @Test
    public void testSaveUserFailure(){
        Movie testMovie = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertNotSame(testMovie.getMovieId(),movie.getMovieId());
    }

    @Test
    public void testGetAllMovie(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        movieRepository.save(m1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Movie1",list.get(0).getMovieTitle());
    }

    @Test
    public void testDeleteMovie(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        movieRepository.save(m1);
        movieRepository.delete(m);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals(1,list.size());

    }

    @Test
    public void testDeleteMovieFailure(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        movieRepository.save(m1);
        movieRepository.delete(m);
        List<Movie> list = movieRepository.findAll();
        Assert.assertNotEquals(2,list.size());
    }

    @Test
    public void testUpdateMovie(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        //Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        m.setMovieTitle("Movie2");
        movieRepository.save(m);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Movie2",list.get(0).getMovieTitle());
    }

    @Test
    public void testFindMovieById(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        //Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        Movie fetchMovie = movieRepository.findByTitleAndDate("Movie1","23/3/12").get(0);
        Assert.assertEquals(m,movieRepository.findById(fetchMovie.getMovieId()).get());
    }

    @Test
    public void testMovieByTitle(){
        Movie m = new Movie("Movie1",4.8f,"djsshds","23/3/12");
        //Movie m1 = new Movie("Movie2",4.8f,"djsshds","23/3/12");
        movieRepository.save(m);
        List<Movie> list = movieRepository.findByTitle("Movie1");
        Assert.assertSame(m,list.get(0));
    }
}
