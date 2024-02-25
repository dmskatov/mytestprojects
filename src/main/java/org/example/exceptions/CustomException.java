package org.example.exceptions;

import org.apache.coyote.BadRequestException;

public class CustomException extends BadRequestException {

    public  CustomException (String message) {
        super(message);
    }
}
