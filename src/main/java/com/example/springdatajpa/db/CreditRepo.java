package com.example.springdatajpa.db;

import com.example.springdatajpa.model.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditRepo extends CrudRepository<Credit, Integer> {

    Credit findByMovieIdAndActorId(int actorID, int movieID);
    public List<Credit> findByMovieId(int movieID);
    public List<Credit>  findByActorId(int actorID);

}
