package io.spring.trucker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReadingNotFound extends RuntimeException {
    public ReadingNotFound(String message) {
        super(message);
    }
}
