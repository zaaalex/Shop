package com.zorkoalex.shop.rest.advice;

import com.zorkoalex.shop.exception.CakeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//если происходят какие-то ошибки, обрабатываем их здесь
//следит за конкретным контроллером
@ControllerAdvice
public class CakeAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CakeNotFoundException.class)
    public void cakeNotFound(){

    }
}
