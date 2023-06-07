package com.gamelog.gamelog.model.game;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table
@Embeddable
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private LocalDate launchDate;

    private String developer;

    private String publisher;

    private String genres;

    private Double rating;

}
