package com.gamelog.gamelog.controller;

import com.gamelog.gamelog.model.game.Game;
import com.gamelog.gamelog.model.game.GameDto;
import com.gamelog.gamelog.model.game.GameRegisterDto;
import com.gamelog.gamelog.model.game.GameRepository;
import com.gamelog.gamelog.model.user.User;
import com.gamelog.gamelog.model.user.UserDto;
import com.gamelog.gamelog.model.user.UserRepository;
import jakarta.transaction.Transactional;
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
    public Iterable<Game> listGames(){
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameByid(@PathVariable Integer id){
        Game game = gameRepository.findById(id).get();
        return new ResponseEntity<Game>(game, HttpStatus.OK);
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
