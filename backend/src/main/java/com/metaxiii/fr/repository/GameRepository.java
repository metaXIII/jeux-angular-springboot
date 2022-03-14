package com.metaxiii.fr.repository;

import com.metaxiii.fr.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByStatus(int status);

    ArrayList<Game> findAllByStatus(int code);
}
