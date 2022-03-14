package com.metaxiii.fr.enums;

public enum Status {
    NOT_STARTED(1),
    IN_PROGRESS(2),
    FINISHED(3),
    GIVE_UP(4),
    INVALID(5);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
