package com.gamelog.gamelog.model.game.dto;

import com.gamelog.gamelog.model.game.Status;

public record GameDto(String name, double rating, Status status) {
}
