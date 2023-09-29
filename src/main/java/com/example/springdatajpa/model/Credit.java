package com.example.springdatajpa.model;

import jakarta.persistence.*;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "MovieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "ActorID")
    private Actor actor;

    private String role;

    public Credit() {
    }

    public Credit(int id, Movie movie, Actor actor) {
        this.id = id;
        this.movie = movie;
        this.actor = actor;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", movie=" + movie +
                ", actor=" + actor +
                ", role='" + role + '\'' +
                '}';
    }
}

