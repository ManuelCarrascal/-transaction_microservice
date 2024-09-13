package emazon.transaction.domain.exception;

public class InternalServerErrorException  extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
