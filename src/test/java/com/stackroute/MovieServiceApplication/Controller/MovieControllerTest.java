package com.stackroute.MovieServiceApplication.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MovieServiceApplication.controller.MovieController;
import com.stackroute.MovieServiceApplication.customException.MovieAlreadyExistException;
import com.stackroute.MovieServiceApplication.customException.MovieNotFoundException;
import com.stackroute.MovieServiceApplication.domain.Movie;
import com.stackroute.MovieServiceApplication.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie();
        movie.setMovieId(1);
        movie.setVoteAverage(5.7f);
        movie.setMovieOverview("first");
        movie.setMovieTitle("Movie1");
        movie.setMovieReleaseDate("23/12/12");
        list = new ArrayList();
        list.add(movie);
    }

    @Test
    public void saveMovie() throws Exception {
        when(movieService.saveMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMovie() throws Exception {
        when(movieService.getAllMovie()).thenReturn(list);
        System.out.println(movieService.getAllMovie().toString());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/movies")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getMovieByIdSuccess() throws Exception {
        when(movieService.getMovieById(anyInt())).thenReturn(movie);

        //System.out.println(movieService.getMovieById(1).toString());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/movie/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getMovieByIdFailure() throws Exception {
        when(movieService.getMovieById(anyInt())).thenThrow(MovieNotFoundException.class);

        //System.out.println(movieService.getMovieById(1).toString());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/movie/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie(0)).thenReturn(list);
        System.out.println(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteMovieFailure() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/api/movie/8"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateMovieSuccess() throws Exception {
        when(movieService.updateMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void updateMovieFailure() throws Exception {
        when(movieService.updateMovie(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieByTitleSuccess() throws Exception {
        when(movieService.getMovieByTitle((String) any())).thenReturn(list);

        //System.out.println(movieService.getMovieById(1).toString());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/movieByName/shk"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}