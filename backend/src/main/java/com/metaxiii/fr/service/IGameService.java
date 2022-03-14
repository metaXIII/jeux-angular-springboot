package com.metaxiii.fr.service;

import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.exception.GameException;
import com.metaxiii.fr.model.Game;

import java.util.List;
import java.util.Optional;

public interface IGameService {
    Optional<Game> findCurrent();

    void finished(int id) throws GameException;

    void giveUp(int id);

    void play();

    Game insert(GameDto gameDto);

    List<Game> list();
}
