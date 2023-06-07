package com.gamelog.gamelog.model.user;

import com.gamelog.gamelog.model.game.Game;
import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    @Embedded
    private Game game;
}
