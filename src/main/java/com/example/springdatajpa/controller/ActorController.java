package com.example.springdatajpa.controller;


import com.example.springdatajpa.db.ActorRepo;
import com.example.springdatajpa.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/actors")
// URL = http://localhost:8080/actors
public class ActorController {

    @Autowired
    ActorRepo actorRepo;


    @GetMapping("")
    // URL = http://localhost:8080/actors/
    public Iterable<Actor> getAll() {
        return actorRepo.findAll();
    }

    @GetMapping("/{id}")
    // URL = http://localhost:8080/actors/{id}
    public Actor getById(@PathVariable int id) {
        Actor actor = new Actor();
        Optional<Actor> optionalActor = actorRepo.findById(id);

        if (optionalActor.isPresent()) {
            actor = optionalActor.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found.");
        }
        return actor;
    }

//    //
    @GetMapping("/by-movie-title/")
    // URL = http://localhost:8080/actors/byMovieTitle"
    public List<Actor> getActorsByMovieTitle(@RequestParam String title) {
        List<Actor> actorsInMovie = actorRepo.findByCreditsMovieTitle(title);

        if (actorsInMovie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found.");
        }

        return actorsInMovie;
    }

    @PostMapping("")
    public Actor create(@RequestBody Actor newActor) {
        Actor actor = new Actor();
        Actor existingActor = actorRepo.findByFirstNameAndLastNameAndBirthDate(newActor.getFirstName(), newActor.getLastName(), newActor.getBirthDate());

        if (existingActor == null) {
            actor = actorRepo.save(newActor);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Actor already exists.");
        }
        return actor;
    }


    @PutMapping("")
    public Actor update(@RequestBody Actor updatedActor) {
        Actor actor = new Actor();
        boolean actorExists = actorRepo.findById(updatedActor.getId()).isPresent();

        if (actorExists) {
            actor = actorRepo.save(updatedActor);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found.");
        }
        return actor;
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable int id) {
        Actor actor = new Actor();
        Optional<Actor> optionalActor = actorRepo.findById(id);
        boolean actorExists = optionalActor.isPresent();

        if (actorExists) {
            actor = optionalActor.get();
            System.out.println("Actor " + actor.getFirstName() + " " + actor.getLastName() + " has been deleted.");
            actorRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found.");
        }
        return actor;
    }
}