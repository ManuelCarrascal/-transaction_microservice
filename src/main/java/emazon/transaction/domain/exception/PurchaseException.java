package emazon.transaction.domain.exception;

public class PurchaseException extends RuntimeException {
    public PurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
