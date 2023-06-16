package com.gamelog.gamelog.controller;

import com.gamelog.gamelog.model.game.*;
import com.gamelog.gamelog.model.user.User;
import com.gamelog.gamelog.model.user.UserDto;
import com.gamelog.gamelog.model.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;



    
    @GetMapping("")
    public List<GameProjection> listGames(){
        return gameRepository.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameViewDto> getGameByid(@PathVariable Integer id){
        Game game = gameRepository.findById(id).get();
        GameViewDto gameViewDto = new GameViewDto(game);
        gameViewDto.setRating(gameRepository.getAverageRating(gameViewDto.getName()));
        return new ResponseEntity<GameViewDto>(gameViewDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Transactional
    public void saveGame(@RequestBody GameRegisterDto gameRegisterDto){
        gameRepository.save(new Game(gameRegisterDto));
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateGame(@RequestBody GameRegisterDto gameRegisterDto, @PathVariable Integer id){
        var game = gameRepository.findById(id).get();
        game.atualizarInformacoes(gameRegisterDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteGame(@PathVariable Integer id){
        gameRepository.deleteById(id);
    }
}
