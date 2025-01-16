package io.github.teamtracker.exception;

public class AccountException extends RuntimeException {

    private int errorCode;

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, int errorCode) {
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