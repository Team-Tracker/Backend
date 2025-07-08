package io.github.teamtracker.exception;

public class TeamException extends IllegalArgumentException {

    private int errorCode;

    public TeamException(String message) {
        super(message);
    }

    public TeamException(String message, int errorCode) {
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