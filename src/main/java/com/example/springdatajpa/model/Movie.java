package com.example.springdatajpa.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year;
    private String rating;
    private String director;

//    @OneToMany(mappedBy = "movie")
//    private List<Credit> credits;

    public Movie() {
    }

    public Movie(int id, String title, int year, String rating, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "A movie was instantiated: " + title + " (" + year + ") is rated " +
                rating.toUpperCase() + " and is directed by " + director + ".\n";
    }
}
