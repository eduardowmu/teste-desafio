package br.edu.infuse.app.controller;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    /*Se lançarmos uma exceção e for do tipo BAD_REQUEST, teremos que usar o seguinte método*/
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException be) {
        return new ResponseEntity<>(new BadRequestExceptionDetails("Bad request Exception",
                HttpStatus.BAD_REQUEST.value(), be.getMessage(), be.getClass().getName(),
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /*Copiado da Classe ResponseEntityExceptionHandler*/
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BadRequestExceptionDetails exception = new BadRequestExceptionDetails(ex.getCause().getMessage(),
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getClass().getName(), LocalDateTime.now());
        return new ResponseEntity<>(exception, headers, status);
    }
}