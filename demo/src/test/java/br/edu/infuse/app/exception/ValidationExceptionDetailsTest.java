package br.edu.infuse.app.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class ValidationExceptionDetailsTest {
    private ValidationExceptionDetails validationExceptionDetails;

    @Test
    void validationExceptionDetailsTest() {
        this.validationExceptionDetails = new ValidationExceptionDetails("", HttpStatus.NO_CONTENT.value(),
        "", "", LocalDateTime.now(), "", "");
        Assertions.assertNotNull(this.validationExceptionDetails);
    }
}