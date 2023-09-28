package com.example.springdatajpa.db;

import com.example.springdatajpa.model.Credit;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepo extends CrudRepository<Credit, Integer> {

    Credit findByMovieIdAndActorId(int actorID, int movieID);

}
