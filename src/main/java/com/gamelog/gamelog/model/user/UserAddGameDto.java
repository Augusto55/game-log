package com.gamelog.gamelog.model.user;

import com.gamelog.gamelog.model.game.Status;

public record UserAddGameDto(Double rating, Status status) {

}
