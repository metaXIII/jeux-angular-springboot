package com.metaxiii.fr.controller;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.service.IConsoleService;
import com.metaxiii.fr.service.IGameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {
    @InjectMocks
    private GameController controller;

    @Mock
    private IGameService gameService;

    @Mock
    private IConsoleService consoleService;

    @Test
    public void shouldFindCurrentGame() {
        Mockito.when(gameService.findCurrent()).thenReturn(Optional.of(currentGame()));
        HttpEntity<Game> current = this.controller.findCurrent();
        assertDoesNotThrow(() -> current);
        Game control = current.getBody();
        assertEquals(1, control.getId());
    }

    @Test
    public void shouldSetTheGameToFinished() {
        assertDoesNotThrow(() -> this.controller.finished(1));
    }

    @Test
    public void shouldSetTheGameToGiveUp() {
        assertDoesNotThrow(() -> this.controller.giveUp(1));
    }

    @Test
    public void shouldSetTheGameToPlay() {
        assertDoesNotThrow(() -> this.controller.play());
    }

    @Test
    public void shouldListGames() {
        Mockito.when(gameService.list()).thenReturn(List.of(currentGame(), currentGame()));
        HttpEntity<List<Game>> current = this.controller.listGames();
        assertDoesNotThrow(() -> current);
        List<Game> control = current.getBody();
        assertEquals(2, control.size());
    }

    @Test
    public void shouldListConsole() {
        Mockito.when(consoleService.list()).thenReturn(List.of(currentConsole(), currentConsole()));
        HttpEntity<List<Console>> current = this.controller.listConsole();
        assertDoesNotThrow(() -> current);
        List<Console> control = current.getBody();
        assertEquals(2, control.size());
    }

    @Test
    public void shouldInsertGame() {
        GameDto gameDto = new GameDto();
        gameDto.setName("new name");
        gameDto.setConsole(currentConsole().getId());
        Mockito.when(gameService.insert(gameDto)).thenReturn(currentGame());
        HttpEntity<Game> current = this.controller.insertGame(gameDto);
        assertDoesNotThrow(() -> current);
        Game game = current.getBody();
        assertEquals(1, game.getId());
    }

    @Test
    public void shouldInsertConsole() {
        ConsoleDto consoleDto = new ConsoleDto();
        consoleDto.setName("new name");
        Mockito.when(consoleService.insert(consoleDto)).thenReturn(currentConsole());
        HttpEntity<Console> current = this.controller.insertConsole(consoleDto);
        assertDoesNotThrow(() -> current);
        Console game = current.getBody();
        assertEquals(1, game.getId());
    }

    private Game currentGame() {
        Console console = currentConsole();
        Game game = new Game();
        game.setId(1);
        game.setName("name");
        game.setConsole(console);
        game.setStatus(2);
        return game;
    }

    private Console currentConsole() {
        Console console = new Console();
        console.setId(1);
        console.setName("nameConsole");
        return console;
    }

//    public HttpEntity<Void> finished(@PathVariable int id) throws GameException {
//        gameService.finished(id);
//        return ResponseEntity.accepted()
//                .build();
//    }
//
//    public HttpEntity<Void> giveUp(@PathVariable int id) {
//        gameService.giveUp(id);
//        return ResponseEntity.accepted()
//                .build();
//    }
//
//    public HttpEntity<Void> play() {
//        gameService.play();
//        return ResponseEntity.accepted()
//                .build();
//    }
//
//    public HttpEntity<List<Game>> listGames() {
//        List<Game> games = gameService.list();
//        return ResponseEntity
//                .ok()
//                .body(games.stream().toList());
//    }
//
//    public HttpEntity<List<Console>> listConsole() {
//        List<Console> consoles = consoleService.list();
//        return ResponseEntity
//                .ok()
//                .body(consoles.stream().toList());
//    }
//
//    public HttpEntity<Game> insertGame(@RequestBody GameDto gameDto) {
//        Game game = gameService.insert(gameDto);
//        return ResponseEntity
//                .ok()
//                .body(game);
//    }
//
//    public HttpEntity<Console> insertConsole(@RequestBody ConsoleDto consoleDto) {
//        Console console = consoleService.insert(consoleDto);
//        return ResponseEntity
//                .ok()
//                .body(console);
//    }

}
