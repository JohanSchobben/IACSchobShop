package iac.schobshop.Schobshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ObjectCreationException extends RuntimeException {
    public ObjectCreationException(String s) {
        super(s);
    }
}
