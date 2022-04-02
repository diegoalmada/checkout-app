package br.com.diegoalmada.checkout.exception;

public class ApiUnavailableException extends RuntimeException {
    public ApiUnavailableException(Throwable throwable) {
        super("API is unavailable", throwable);
    }
}
