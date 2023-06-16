package com.gamelog.gamelog.model.user;

import com.gamelog.gamelog.model.game.Status;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UserAddGameDto(@Min(0)
                             @Max(5)
                             double rating,
                             Status status) {

}
