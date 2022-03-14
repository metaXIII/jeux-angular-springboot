package com.metaxiii.fr.transformer;

import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.service.IConsoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameTransformer {

    private IConsoleService consoleService;

    public Game toModel(GameDto gameDto) {
        Game game = new Game();
        game.setName(gameDto.getName());
        game.setConsole(consoleService.getById(gameDto.getConsole()));
        return game;
    }
}
