package com.gamelog.gamelog.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamelog.gamelog.model.user.User;
import com.gamelog.gamelog.model.user.UserAddGameDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "game")
public class Game implements Cloneable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name="launchdate")
    private LocalDate launchDate;

    private String developer;

    private String publisher;

    private String genres;

    @Column(unique = true)
    private Double rating;

    @Column(unique = true)
    private Status status;



    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Game() {

    }

    public Game(GameRegisterDto gameRegisterDto) {
        this.id = gameRegisterDto.id();
        this.name = gameRegisterDto.name();
        this.launchDate = gameRegisterDto.launchDate();
        this.developer = gameRegisterDto.developer();
        this.publisher = gameRegisterDto.publisher();
        this.genres = gameRegisterDto.genres();
    }

    public Game(UserAddGameDto addgame) {
        this.rating = addgame.rating();
        this.status = addgame.status();
    }

    public void addRatingStatus(UserAddGameDto addGameDto){
        this.rating = addGameDto.rating();
        this.status = addGameDto.status();
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



    public void atualizarInformacoes(GameRegisterDto gameRegisterDto){
        this.name = gameRegisterDto.name();
        this.launchDate = gameRegisterDto.launchDate();
        this.developer = gameRegisterDto.developer();
        this.publisher = gameRegisterDto.publisher();
        this.genres = gameRegisterDto.genres();
    }
}
