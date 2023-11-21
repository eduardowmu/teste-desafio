package br.edu.infuse.app.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder//funcionará como super classe do BadRequestExceptionDEtails
public class ExceptionDetails {
	protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timeStamp;

    public ExceptionDetails(String title, int status, String details,
                            String developerMessage, LocalDateTime timeStamp) {
        this.title = title;
        this.status = status;
        this.details = details;
        this.developerMessage = developerMessage;
        this.timeStamp = timeStamp;
    }
}