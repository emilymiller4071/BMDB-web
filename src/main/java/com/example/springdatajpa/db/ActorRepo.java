package com.example.springdatajpa.db;

import com.example.springdatajpa.model.Actor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface ActorRepo extends CrudRepository<Actor, Integer> {

//    List<Actor> getActorsByMovieTitle(String title);
Actor findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, LocalDate birthDate);
    List<Actor> findByCreditsMovieTitle(String title);
}
