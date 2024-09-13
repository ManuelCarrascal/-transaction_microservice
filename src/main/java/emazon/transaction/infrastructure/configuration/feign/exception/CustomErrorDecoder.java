package emazon.transaction.infrastructure.configuration.feign.exception;

import emazon.transaction.domain.exception.BadRequestException;
import emazon.transaction.domain.exception.InternalServerErrorException;
import emazon.transaction.domain.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.HashMap;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private static final Map<Integer, String> EXCEPTION_MESSAGES = new HashMap<>();

    static {
        EXCEPTION_MESSAGES.put(400, "Bad Request");
        EXCEPTION_MESSAGES.put(404, "Not Found");
        EXCEPTION_MESSAGES.put(500, "Internal Server Error");
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        String message = EXCEPTION_MESSAGES.getOrDefault(response.status(), "Generic error");
        return switch (response.status()) {
            case 400 -> new BadRequestException(message);
            case 404 -> new NotFoundException(message);
            case 500 -> new InternalServerErrorException(message);
            default -> new Exception(message);
        };
    }
}