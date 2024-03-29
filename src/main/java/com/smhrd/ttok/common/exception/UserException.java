package com.smhrd.ttok.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public UserException(String message, HttpStatus status){
        super(message);
        this.message = message;
        this.status = status;
    }
    
}
