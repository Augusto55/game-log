package com.gamelog.gamelog.controller;

import com.gamelog.gamelog.model.game.Game;
import com.gamelog.gamelog.model.game.GameDto;
import com.gamelog.gamelog.model.game.GameRepository;
import com.gamelog.gamelog.model.game.Status;
import com.gamelog.gamelog.model.user.User;
import com.gamelog.gamelog.model.user.UserAddGameDto;
import com.gamelog.gamelog.model.user.UserDto;
import com.gamelog.gamelog.model.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserController(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @GetMapping("")
    public List<User> listUsers(){
        return userRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByid(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Transactional
    public void saveUser(@RequestBody UserDto userDto){
        userRepository.save(new User(userDto));
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateUser(@RequestBody UserDto userDto, @PathVariable Integer id){
        var user = userRepository.getReferenceById(id);
        user.atualizarInformacoes(userDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }



    @PostMapping("/{userId}/addgames/{gameId}")
    @Transactional
    public ResponseEntity<String> addGameToUser(@PathVariable Integer userId, @PathVariable Integer gameId,
                                                @RequestBody UserAddGameDto addGameDto) {

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));


        for (Game userGame : user.getGames()) {
            if (userGame.getName().equals(game.getName())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Jogo já existe na lista");
            }
        }

        Game userGame = new Game();
        userGame.setName(game.getName());
        userGame.setLaunchDate(game.getLaunchDate());
        userGame.setDeveloper(game.getDeveloper());
        userGame.setPublisher(game.getPublisher());
        userGame.setGenres(game.getGenres());
        userGame.setRating(addGameDto.rating());
        userGame.setStatus(addGameDto.status());


        userGame = entityManager.merge(userGame);


        user.getGames().add(userGame);
        return ResponseEntity.ok("Jogo adicionado com sucesso ao usuário.");

    }


    @DeleteMapping("/{userId}/addgames/{gameId}")
    @Transactional
    public ResponseEntity<String> removeGameFromList(@PathVariable Integer userId, @PathVariable Integer gameId,
                                                @RequestBody UserAddGameDto addGameDto) {

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));


        for (Game userGame : user.getGames()) {
            if (userGame.getName().equals(game.getName())) {
                user.games.remove(userGame);
                return ResponseEntity.status(HttpStatus.OK).body("Jogo removido com sucesso");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado na lista.");

    }





}



