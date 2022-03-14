package com.metaxiii.fr.transformer;

import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.model.Console;
import org.springframework.stereotype.Component;

@Component
public class ConsoleTransformer {
    public Console toModel(ConsoleDto consoleDto) {
        Console console = new Console();
        console.setName(consoleDto.getName());
        return console;
    }
}
