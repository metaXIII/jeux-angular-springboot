package com.metaxiii.fr.exception;

import com.metaxiii.fr.enums.GameErrorCode;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameException extends InvalidRequestStateException {
    public GameException(GameErrorCode errorCode, int id) {
        log.error(errorCode.getMessage(), id);
    }

    public GameException(GameErrorCode gameInProgress) {
        log.error(gameInProgress.getMessage());
    }
}
