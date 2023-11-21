package br.edu.infuse.app.controller;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RestExceptionHandlerTest {
    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    void handlerBadRequestExceptionTest() {
        Assertions.assertNotNull(this.restExceptionHandler.handlerBadRequestException(new BadRequestException("")));
    }

    @Test
    void handleExceptionInternalTest() {
        HttpHeaders headers = new HttpHeaders();
        Assertions.assertThrows(NullPointerException.class, () -> this.restExceptionHandler.handleExceptionInternal(
                new BadRequestException(""), new Order(), headers, HttpStatus.BAD_REQUEST, null));
    }
}