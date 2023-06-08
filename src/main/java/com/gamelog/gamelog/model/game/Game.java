package com.gamelog.gamelog.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamelog.gamelog.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private LocalDate launchDate;

    private String developer;

    private String publisher;

    private String genres;

    @Column(unique = true)
    private Double rating;

    @Column(unique = true)
    private Status status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Game(Integer id, String name, LocalDate launchDate, String developer, String publisher, String genres, Double rating, Status status, User user) {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
        this.developer = developer;
        this.publisher = publisher;
        this.genres = genres;
        this.rating = rating;
        this.status = status;
        this.user = user;
    }

    public Game() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
