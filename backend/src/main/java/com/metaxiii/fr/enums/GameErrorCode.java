package com.metaxiii.fr.enums;

public enum GameErrorCode {
    CANT_FIND_GAME_CONCERNED("Impossible de trouver le jeu concerné avec l'id {0}"),
    CANT_FIND_CONSOLE_BY_ID("Impossible de trouver la console avec l'id {0}"),
    GAME_IN_PROGRESS("Un jeu est déjà en cours");

    private final String message;

    GameErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
