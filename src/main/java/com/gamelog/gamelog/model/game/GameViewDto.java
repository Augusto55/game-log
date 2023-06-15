package com.gamelog.gamelog.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameViewDto {

    String name;
    LocalDate launchDate;
    String developer;
    String publisher;
    String genres;
    Double rating;

    public GameViewDto (Game game){
        this.name  = game.getName();
        this.launchDate = game.getLaunchDate();
        this.developer = game.getDeveloper();
        this.publisher = game.getPublisher();
        this.genres = game.getGenres();
        this.rating = game.getRating();
    }
}
