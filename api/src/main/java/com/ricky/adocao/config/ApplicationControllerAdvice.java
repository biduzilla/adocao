package com.ricky.adocao.config;

import com.ricky.adocao.error.ApiError;
import com.ricky.adocao.exception.NotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(NotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEmailJaCadastradoException(NotFoundExeption ex) {
        return new ApiError(ex.getMessage());
    }

}