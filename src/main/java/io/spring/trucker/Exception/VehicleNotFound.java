package io.spring.trucker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VehicleNotFound extends RuntimeException {
    public VehicleNotFound(String message) {
        super(message);
    }
}