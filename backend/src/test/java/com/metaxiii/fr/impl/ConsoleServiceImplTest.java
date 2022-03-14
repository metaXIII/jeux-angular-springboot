package com.metaxiii.fr.impl;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.repository.ConsoleRepository;
import com.metaxiii.fr.transformer.ConsoleTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ConsoleServiceImplTest {

    @InjectMocks
    private ConsoleServiceImpl consoleService;

    @Mock
    private ConsoleRepository repository;

    @Mock
    private ConsoleTransformer transformer;

    @Test
    void list() {
        assertDoesNotThrow(() -> consoleService.list());
    }

    @Test
    void getById() {
        Console console = mockConsole();
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(console));
        assertEquals(console, consoleService.getById(1));
    }


    @Test
    void insert() {
        Console console = mockConsole();
        Mockito.when(repository.save(any())).thenReturn(console);
        assertEquals(console, consoleService.insert(new ConsoleDto()));
    }

    private Console mockConsole() {
        Console console = new Console();
        console.setId(1);
        console.setName("console Mocked");
        return console;
    }
}
