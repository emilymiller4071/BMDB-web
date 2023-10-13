package com.example.springdatajpa.controller;

import com.example.springdatajpa.db.ActorRepo;
import com.example.springdatajpa.db.CreditRepo;
import com.example.springdatajpa.db.MovieRepo;
import com.example.springdatajpa.model.Actor;
import com.example.springdatajpa.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/credits")
// URL = http://localhost:8080/credits
public class CreditController {

    @Autowired
    ActorRepo actorRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    CreditRepo creditRepo;


    // look into diff between List and Interable

    @GetMapping("")
    // URL = http://localhost:8080/credits/
    public Iterable<Credit> getAll() {
        return creditRepo.findAll();
    }

    @GetMapping("/{id}")
    // URL = http://localhost:8080/credits/{id}
    public Credit getById(@PathVariable int id) {
        Credit credit = new Credit();
        Optional<Credit> optionalCredit = creditRepo.findById(id);

        if (optionalCredit.isPresent()) {
            credit = optionalCredit.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found.");
        }
        return credit;
    }

    @GetMapping("/movie/{movieID}")
    // URL = http://localhost:8080/credits/movie/{movieID}
    public List<Credit> getByMovieId(@PathVariable int movieID) {
        List<Credit> credits = creditRepo.findByMovieId(movieID);

        if (credits.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No credits found for that movie.");
        }
        return credits;
    }

    @GetMapping("/actor/{actorID}")
    // URL = http://localhost:8080/credits/actor/{actorID}
    public List<Credit> getByActorId(@PathVariable int actorID) {
        List<Credit> credits = creditRepo.findByActorId(actorID);

        if (credits.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No credits found for that actor.");
        }
        return credits;
    }

    // check ActorID and MovieID combo
    @PostMapping("")
    public Credit create(@RequestBody Credit newCredit) {
        Credit credit = new Credit();
        Credit existingCredit = creditRepo.findByMovieIdAndActorId(newCredit.getActor().getId(), newCredit.getMovie().getId());

        if (existingCredit == null) {
            credit = creditRepo.save(newCredit);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Credit already exists.");
        }
        return credit;
    }

    @PutMapping("")
    public Credit update(@RequestBody Credit updatedCredit) {
        Credit credit = new Credit();
        boolean creditExists = creditRepo.existsById(updatedCredit.getId());
        if (creditExists) {
            credit = creditRepo.save(updatedCredit);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found.");
        }
        return credit;
    }

    @DeleteMapping("/{id}")
    public Credit delete(@PathVariable int id) {
        Credit credit = new Credit();
        Optional<Credit> optionalCredit = creditRepo.findById(id);
        boolean creditExists = optionalCredit.isPresent();

        if (creditExists) {
            credit = optionalCredit.get();
            creditRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found.");
        }
        return credit;
    }
}
