package com.gamelog.gamelog.model.game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer> {

//    @Query("select distinct g.id, g.name, g.rating from Game g")
//    public List<String> getAllGames();

//    @Query("select distinct g.id, g.name, g.launchDate, g.developer, g.publisher, g.genres, g.rating from Game g")
//    public Iterable<Game> getAllGames();

    @Query(value = "select new com.gamelog.gamelog.model.game.GameViewDto(g.id, g.name, g.launchDate, g.developer, g.publisher, g.genres, g.rating) from Game g")
    public List<GameViewDto> getAllGames();

    @Query("SELECT AVG(g.rating) FROM Game g WHERE g.name= :game_name")
    public Double getAverageRating(@Param("game_name") String game_name);
}
