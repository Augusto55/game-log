package com.gamelog.gamelog.controller;

import com.gamelog.gamelog.model.game.Game;
import com.gamelog.gamelog.model.game.GameDto;
import com.gamelog.gamelog.model.game.GameRepository;
import com.gamelog.gamelog.model.game.Status;
import com.gamelog.gamelog.model.user.User;
import com.gamelog.gamelog.model.user.UserAddGameDto;
import com.gamelog.gamelog.model.user.UserDto;
import com.gamelog.gamelog.model.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

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



//    @PostMapping("/{userId}/addgames/{gameId}")
//    @Transactional
//    public ResponseEntity<String> addGameToUser(@PathVariable Integer userId, @PathVariable Integer gameId,
//                                                @RequestBody UserAddGameDto addGameDto){
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado."));
//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
//
//        user.games.add(game);
//        game.addRatingStatus(addGameDto);
//
//        return ResponseEntity.ok("Jogo adicionado com sucesso ao usuário.");
//    }
//
//

    @PostMapping("/{userId}/addgames/{gameId}")
    @Transactional
    public ResponseEntity<String> addGameToUser(@PathVariable Integer userId, @PathVariable Integer gameId,
                                                @RequestBody UserAddGameDto addGameDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        game.setRating(addGameDto.rating());
        game.setStatus(addGameDto.status());

        // Adiciona o jogo à lista de jogos do usuário
        user.getGames().add(game);

        return ResponseEntity.ok("Jogo adicionado com sucesso ao usuário.");
    }

}
