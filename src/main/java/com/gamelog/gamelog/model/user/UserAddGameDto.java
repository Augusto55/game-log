package com.gamelog.gamelog.model.user;

import com.gamelog.gamelog.model.game.Status;

public record UserAddGameDto(double rating, Status status) {

}
