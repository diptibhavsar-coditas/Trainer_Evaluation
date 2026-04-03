package com.example.Ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
    private String path;

    public ErrorResponse(int status, String message, LocalDateTime dateTime, String path) {
        super();
        this.status = status;
        this.message = message;
        this.dateTime = dateTime;
        this.path=path;
    }

}
