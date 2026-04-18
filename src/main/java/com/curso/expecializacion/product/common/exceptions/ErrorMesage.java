package com.curso.expecializacion.product.common.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ErrorMesage {
    private String message;
    private String exception;
    private String path;
    private Map<String, String> errors;

    public ErrorMesage(String message, String exception, String path) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = new HashMap<>();
    }

    public ErrorMesage(String message, String exception, String path, Map<String, String> errors) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = errors;
    }
}
