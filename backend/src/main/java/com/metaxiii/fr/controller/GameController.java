package com.metaxiii.fr.controller;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.exception.GameException;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.service.IConsoleService;
import com.metaxiii.fr.service.IGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class GameController {

    private final IGameService gameService;
    private final IConsoleService consoleService;

    @GetMapping("game")
    public HttpEntity<Game> findCurrent() {
        Optional<Game> game = gameService.findCurrent();
        return game.<HttpEntity<Game>>map(value -> ResponseEntity.ok()
                .body(value)).orElseGet(() -> ResponseEntity.noContent()
                .build());
    }

    @GetMapping("finished/{id}")
    public HttpEntity<Void> finished(@PathVariable int id) throws GameException {
        gameService.finished(id);
        return ResponseEntity.accepted()
                .build();
    }

    @GetMapping("giveup/{id}")
    public HttpEntity<Void> giveUp(@PathVariable int id) {
        gameService.giveUp(id);
        return ResponseEntity.accepted()
                .build();
    }

    @GetMapping("play")
    public HttpEntity<Void> play() {
        gameService.play();
        return ResponseEntity.accepted()
                .build();
    }

    @GetMapping("listGames")
    public HttpEntity<List<Game>> listGames() {
        List<Game> games = gameService.list();
        return ResponseEntity
                .ok()
                .body(games.stream().toList());
    }

    @GetMapping("listConsole")
    public HttpEntity<List<Console>> listConsole() {
        List<Console> consoles = consoleService.list();
        return ResponseEntity
                .ok()
                .body(consoles.stream().toList());
    }

    @PostMapping("game")
    public HttpEntity<Game> insertGame(@RequestBody GameDto gameDto) {
        Game game = gameService.insert(gameDto);
        return ResponseEntity
                .ok()
                .body(game);
    }

    @PostMapping("console")
    public HttpEntity<Console> insertConsole(@RequestBody ConsoleDto consoleDto) {
        Console console = consoleService.insert(consoleDto);
        return ResponseEntity
                .ok()
                .body(console);
    }
}
