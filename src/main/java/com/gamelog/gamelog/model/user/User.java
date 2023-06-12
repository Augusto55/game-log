package com.gamelog.gamelog.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamelog.gamelog.model.game.Game;
import com.gamelog.gamelog.model.game.GameCopy;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_game",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "game_id") })
    public List<Game> games = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String username, List<Game> games) {
        this.id = id;
        this.username = username;
        this.games = games;
    }

    public User(UserDto userDto) {
        this.username = userDto.username();
        this.id = userDto.id();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void atualizarInformacoes(UserDto userDto) {
        this.username = userDto.username();
    }
}
