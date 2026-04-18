package com.curso.expecializacion.product.common.exceptions;


import com.curso.expecializacion.product.domain.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorMesage badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return new ErrorMesage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI(), errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseBody
    public ErrorMesage notFund(HttpServletRequest request, Exception exception) {

        return new ErrorMesage(exception.getMessage(), exception.getClass().getSimpleName(), request.getRequestURI());
    }
}
