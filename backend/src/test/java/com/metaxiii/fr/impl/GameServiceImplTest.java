package com.metaxiii.fr.impl;

import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.exception.GameException;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.repository.GameRepository;
import com.metaxiii.fr.transformer.GameTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository repository;

    @Mock
    private GameTransformer transformer;

    @Test
    void findCurrent() {
        Game game = mockGame();
        Mockito.when(repository.findByStatus(2)).thenReturn(Optional.of(game));
        assertDoesNotThrow(() -> this.gameService.findCurrent());
        assertEquals(game, this.gameService.findCurrent().get());
    }

    @Test
    void finished() {
        assertThrows(GameException.class, () -> this.gameService.finished(1));
        Mockito.when(repository.findByStatus(2)).thenReturn(Optional.of(mockGame()));
        assertDoesNotThrow(() -> this.gameService.finished(1));
    }

    @Test
    void giveUp() {
        assertThrows(GameException.class, () -> this.gameService.giveUp(1));
        Mockito.when(repository.findByStatus(2)).thenReturn(Optional.of(mockGame()));
        assertDoesNotThrow(() -> this.gameService.giveUp(1));
    }

    @Test
    void play() {
        Mockito.when(repository.findByStatus(2)).thenReturn(Optional.of(mockGame()));
        assertThrows(GameException.class, () -> gameService.play());
        Mockito.when(repository.findByStatus(2)).thenReturn(Optional.empty());
        Mockito.when(repository.findAllByStatus(1)).thenReturn(new ArrayList<Game>(Arrays.asList(mockGame())));
        assertDoesNotThrow(() -> this.gameService.play());
    }

    @Test
    void insert() {
        Game game = mockGame();
        Mockito.when(repository.save(any())).thenReturn(game);
        assertDoesNotThrow(() -> this.gameService.insert(new GameDto()));

    }

    @Test
    void list() {
        assertDoesNotThrow(() -> this.gameService.list());
    }

    private Console MockConsole() {
        Console console = new Console();
        console.setId(1);
        console.setName("console");
        return console;
    }

    private Game mockGame() {
        Game game = new Game();
        game.setId(1);
        game.setName("jeux");
        game.setConsole(MockConsole());
        game.setStatus(2);
        return game;
    }
}