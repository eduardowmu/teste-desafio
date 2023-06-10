package br.edu.infuse.app.exception;

import java.time.LocalDateTime;

public class NotFoundExceptionDetails extends ExceptionDetails {
    public NotFoundExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime timeStamp) {
        super(title, status, details, developerMessage, timeStamp);
    }
}
