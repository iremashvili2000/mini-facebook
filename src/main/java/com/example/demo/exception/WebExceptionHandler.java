package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WebExceptionHandler extends WebSecurityConfigurerAdapter
{

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity handlerDontFoundException(NotFoundException dontFoundException){
        return new ResponseEntity(dontFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity handlerDontFoundException(BadDataException badDataException){
        return new ResponseEntity(badDataException.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
