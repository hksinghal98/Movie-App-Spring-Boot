package com.satckroute.MovieServiceApplication.repository;

import com.satckroute.MovieServiceApplication.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
