package com.example.springdatajpa.controller;

import com.example.springdatajpa.db.MovieRepo;
import com.example.springdatajpa.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/movies")
// URL = http://localhost:8080/movies
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;


    @GetMapping("")
    // URL = http://localhost:8080/movies
    public Iterable<Movie> getAll() {
        return movieRepo.findAll();
    }

    @GetMapping("/{id}")
    // URL = http://localhost:8080/movies/{id}
    public Movie getById(@PathVariable int id) {
        Movie movie = new Movie();
        Optional<Movie> optionalMovie = movieRepo.findById(id);

        if (optionalMovie.isPresent()) {
            movie = optionalMovie.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        return movie;
    }

    @GetMapping("/titles/")
    // URL = http://localhost:8080/movies/titles/ --parameters here
    public List<Movie> getByTitle(@RequestParam String title) {
        List<Movie> moviesByTitle = movieRepo.getByTitle(title);

        if (moviesByTitle.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found with that title.");
        }
        return moviesByTitle;
    }

    @GetMapping("/director")
    // URL = http://localhost:8080/movies/director/
    public List<Movie> getByDirector(@RequestParam String director) {
        List<Movie> moviesByDirector = movieRepo.getByDirector(director);

        if (moviesByDirector.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found by director" + director + " found.");
        }
        return moviesByDirector;
    }

    @GetMapping("/year")
    // URL = http://localhost:8080/movies/year/
    public List<Movie> getByYear(@RequestParam int year) {
        List<Movie> moviesByYear = movieRepo.getByYear(year);

        if (moviesByYear.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies from the year" + year + " found.");
        }
        return moviesByYear;
    }


    @PostMapping("")
    public Movie create(@RequestBody Movie newMovie) {
        Movie movie = new Movie();
        Movie existingMovie = movieRepo.findByTitleAndYear(movie.getTitle(), movie.getYear());

        if (existingMovie == null) {
            movie = movieRepo.save(newMovie);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exists.");
        }
        return movie;
    }

    @PutMapping("")
    public Movie update(@RequestBody Movie updatedMovie) {
        Movie movie = new Movie();

        boolean movieExists = movieRepo.findById(updatedMovie.getId()).isPresent();

        if(movieExists) {
            movie = movieRepo.save(updatedMovie);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found.");
        }
        return movie;
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable int id) {
        Movie movie = new Movie();
        Optional<Movie> optionalMovie = movieRepo.findById(id);
        boolean movieExists = optionalMovie.isPresent();

        if(movieExists) {
            movie = optionalMovie.get();
            System.out.println(movie.getTitle() + " has been deleted.");
            movieRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        return movie;
    }
}

