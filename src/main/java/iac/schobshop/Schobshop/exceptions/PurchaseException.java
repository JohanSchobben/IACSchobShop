package iac.schobshop.Schobshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class PurchaseException extends RuntimeException {
    public PurchaseException(String message) {
        super(message);
    }
}
