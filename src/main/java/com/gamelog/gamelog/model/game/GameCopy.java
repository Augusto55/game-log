package com.gamelog.gamelog.model.game;

import com.gamelog.gamelog.model.user.UserAddGameDto;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class GameCopy {
    public GameCopy(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.launchDate = game.getLaunchDate();
        this.developer = game.getDeveloper();
        this.publisher = game.getPublisher();
        this.genres = game.getGenres();
    }

    private Integer id;

    private String name;

    private LocalDate launchDate;

    private String developer;

    private String publisher;

    private String genres;

    private Double rating;

    private Status status;

    public void setRatingStatus(UserAddGameDto gameDto){
        this.rating = gameDto.rating();
        this.status = gameDto.status();
    }
}
