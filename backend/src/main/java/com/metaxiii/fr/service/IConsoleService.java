package com.metaxiii.fr.service;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.model.Console;

import java.util.List;

public interface IConsoleService {
    List<Console> list();

    Console getById(int console);

    Console insert(ConsoleDto consoleDto);
}
