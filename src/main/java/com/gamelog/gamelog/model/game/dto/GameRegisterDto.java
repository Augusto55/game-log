package com.gamelog.gamelog.model.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public record GameRegisterDto (Integer id, String name, LocalDate launchDate,
                              String developer, String publisher, String genres) {
}
