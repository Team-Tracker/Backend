package io.github.teamtracker.exception;

public class ScrumException extends IllegalArgumentException {

    private int errorCode;

    public ScrumException(String message) {
        super(message);
    }

    public ScrumException(String message, int errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}