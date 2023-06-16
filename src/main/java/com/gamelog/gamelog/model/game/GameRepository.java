package com.gamelog.gamelog.model.game;

import com.gamelog.gamelog.model.game.dto.GameViewDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query(value = "select new com.gamelog.gamelog.model.game.dto.GameViewDto(g.id, g.name, g.launchDate, g.developer, g.publisher, g.genres, AVG(g.rating)) from Game g GROUP BY g.name")
    public List<GameViewDto> getAllGames();

    @Query("SELECT AVG(g.rating) FROM Game g WHERE g.name= :game_name")
    public Double getAverageRating(@Param("game_name") String game_name);
}
