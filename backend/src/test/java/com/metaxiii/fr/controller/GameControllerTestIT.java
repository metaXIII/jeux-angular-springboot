package com.metaxiii.fr.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.dto.ConsoleDto;
import com.metaxiii.fr.dto.GameDto;
import com.metaxiii.fr.model.Console;
import com.metaxiii.fr.model.Game;
import com.metaxiii.fr.repository.ConsoleRepository;
import com.metaxiii.fr.repository.GameRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
@AutoConfigureMockMvc
class GameControllerTestIT {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ConsoleRepository consoleRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        saveGame(1, 2);
    }

    @AfterEach
    public void end() {
        this.consoleRepository.deleteAll();
        this.gameRepository.deleteAll();
    }


    @Test
    void shouldFindCurrentGame() throws Exception {
        ResultActions actions = this.mockMvc
                .perform(get("/api/game"))
                .andDo(print())
                .andExpect(status().isOk());
        Game control = objectMapper.readValue(actions
                .andReturn()
                .getResponse().getContentAsString(), Game.class);
        assertEquals("jeux", control.getName());
        assertEquals("console", control.getConsole().getName());
        assertEquals(2, control.getStatus());
    }

    @Test
    void shouldSetGameToFinishedWhenIdIsProvided() throws Exception {
        this.mockMvc
                .perform(get("/api/finished/1"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void shouldGiveUpWhenIdIsProvided() throws Exception {
        this.mockMvc
                .perform(get("/api/giveup/1"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void shouldThrowsExceptionWhenGameIsAlreadyPlayable() {
        assertThrows(NestedServletException.class, () -> {
            this.mockMvc
                    .perform(get("/api/play"))
                    .andDo(print())
                    .andExpect(status().isAccepted());
        });
    }

    @Test
    void shouldPlayANewGame() throws Exception {
        saveGame(gameRepository.findAll().get(0).getId(), 3);
        saveGame(2, 1);
        this.mockMvc
                .perform(get("/api/play"))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void shouldListGames() throws Exception {
        saveGame(2, 1);
        ResultActions actions = this.mockMvc
                .perform(get("/api/listGames"))
                .andDo(print())
                .andExpect(status().isOk());
        List<Game> control = objectMapper.readValue(actions
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(2, control.size());
        assertEquals("jeux", control.get(0).getName());
        assertEquals("console", control.get(0).getConsole().getName());
        assertEquals(2, control.get(0).getStatus());
        assertEquals("jeux", control.get(1).getName());
        assertEquals("console", control.get(1).getConsole().getName());
        assertEquals(1, control.get(1).getStatus());
    }

    @Test
    void shouldListConsole() throws Exception {
        ResultActions actions = this.mockMvc
                .perform(get("/api/listConsole"))
                .andDo(print())
                .andExpect(status().isOk());
        List<Console> control = objectMapper.readValue(actions
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(2, control.size());
    }

    @Test
    void shouldInsertGameWhenDtoIsProvided() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setConsole(consoleRepository.findAll().get(0).getId());
        gameDto.setName("new name");
        ResultActions actions = this.mockMvc
                .perform(post("/api/game")
                        .content(asJsonString(gameDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Game control = objectMapper.readValue(actions
                .andReturn()
                .getResponse().getContentAsString(), Game.class);
        assertEquals("new name", control.getName());
        assertEquals("console", control.getConsole().getName());
    }

    @Test
    void shouldInsertConsoleWhenDtoIsProvided() throws Exception {
        ConsoleDto consoleDto = new ConsoleDto();
        consoleDto.setName("Console inserted");
        ResultActions actions = this.mockMvc
                .perform(post("/api/console")
                        .content(asJsonString(consoleDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Console control = objectMapper.readValue(actions
                .andReturn()
                .getResponse().getContentAsString(), Console.class);
        assertEquals("Console inserted", control.getName());

    }

    private void saveGame(int id, int status) {
        Console console = new Console();
        console.setId(1);
        console.setName("console");
        consoleRepository.saveAndFlush(console);
        Game game = new Game();
        game.setId(id);
        game.setName("jeux");
        game.setStatus(status);
        game.setConsole(console);
        gameRepository.saveAndFlush(game);

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}