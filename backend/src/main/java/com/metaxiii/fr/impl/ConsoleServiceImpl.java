package com.metaxiii.fr.impl;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.enums.GameErrorCode;
import com.metaxiii.fr.exception.GameException;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.repository.ConsoleRepository;
import com.metaxiii.fr.service.IConsoleService;
import com.metaxiii.fr.transformer.ConsoleTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ConsoleServiceImpl implements IConsoleService {
    private final ConsoleRepository repository;
    private final ConsoleTransformer transformer;

    @Override
    public List<Console> list() {
        return repository.findAll();
    }

    @Override
    public Console getById(int console) {
        return repository.findById(console)
                .orElseThrow(() -> new GameException(GameErrorCode.CANT_FIND_CONSOLE_BY_ID, console));
    }

    @Override
    public Console insert(ConsoleDto consoleDto) {
        return repository.save(transformer.toModel(consoleDto));
    }
}
