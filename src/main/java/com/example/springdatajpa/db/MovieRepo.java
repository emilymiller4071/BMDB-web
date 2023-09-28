package com.example.springdatajpa.db;

import com.example.springdatajpa.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepo extends CrudRepository<Movie, Integer> {

    Movie findByTitleAndYear(String title, int year);
    List<Movie> getByTitle(String title);
    List<Movie> getByDirector(String director);
    List<Movie> getByYear(int year);
}
