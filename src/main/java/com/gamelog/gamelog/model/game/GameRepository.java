package com.gamelog.gamelog.model.game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends CrudRepository<Game, Integer> {
    @Query("SELECT AVG(g.rating) FROM Game g WHERE g.name= :game_name")
    public Double getAverageRating(@Param("game_name") String game_name);
}
