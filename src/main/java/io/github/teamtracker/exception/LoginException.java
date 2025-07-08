package io.github.teamtracker.exception;

public class LoginException extends RuntimeException {

    private int errorCode;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, int errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}