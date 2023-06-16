package com.gamelog.gamelog.model.game;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GameViewDto {

    Integer id;
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

    public GameViewDto(Integer id, String name, LocalDate launchDate, String developer, String publisher, String genres, Double rating) {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
        this.developer = developer;
        this.publisher = publisher;
        this.genres = genres;
        this.rating = rating;
    }
}
