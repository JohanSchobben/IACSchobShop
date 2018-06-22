package iac.schobshop.Schobshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String object, String identifierType, String identifier){
        super("can't find "+ object + " with "+ identifierType +" "+ identifier);
    }
}


