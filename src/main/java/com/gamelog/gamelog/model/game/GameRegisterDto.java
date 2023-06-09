package com.gamelog.gamelog.model.game;

import java.time.LocalDate;

public record GameRegisterDto (Integer id, String name, LocalDate launchDate,
                              String developer, String publisher, String genres) {
}
