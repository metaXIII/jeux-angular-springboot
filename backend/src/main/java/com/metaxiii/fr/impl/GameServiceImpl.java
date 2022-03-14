package com.metaxiii.fr.impl;

import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.enums.Status;
import com.metaxiii.fr.exception.GameException;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.repository.GameRepository;
import com.metaxiii.fr.service.IGameService;
import com.metaxiii.fr.transformer.GameTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.metaxiii.fr.enums.GameErrorCode.CANT_FIND_GAME_CONCERNED;
import static com.metaxiii.fr.enums.GameErrorCode.GAME_IN_PROGRESS;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class GameServiceImpl implements IGameService {
    private final GameRepository repository;
    private final GameTransformer transformer;

    @Override
    public Optional<Game> findCurrent() {
        return repository.findByStatus(Status.IN_PROGRESS.getCode());
    }

    @Override
    public void finished(int id) throws GameException {
        this.findCurrent()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.FINISHED.getCode());
                    repository.save(game);
                }, () -> {
                    throw new GameException(CANT_FIND_GAME_CONCERNED, id);
                });
    }

    @Override
    public void giveUp(int id) {
        this.findCurrent()
                .ifPresentOrElse(game -> {
                    game.setStatus(Status.GIVE_UP.getCode());
                    repository.save(game);
                }, () -> {
                    throw new GameException(CANT_FIND_GAME_CONCERNED, id);
                });
    }

    @Override
    public void play() {
        this.findCurrent()
                .ifPresentOrElse(
                        game -> {
                            throw new GameException(GAME_IN_PROGRESS);
                        }, this::playNewGame);
    }

    private void playNewGame() {
        ArrayList<Game> games = repository.findAllByStatus(Status.NOT_STARTED.getCode());
        Random random = new Random();
        Game game = games.get(random.nextInt(games.size()));
        game.setStatus(Status.IN_PROGRESS.getCode());
        repository.save(game);
    }

    @Override
    public Game insert(GameDto gameDto) {
        return repository.save(transformer.toModel(gameDto));
    }

    @Override
    public List<Game> list() {
        return repository.findAll();
    }
}
