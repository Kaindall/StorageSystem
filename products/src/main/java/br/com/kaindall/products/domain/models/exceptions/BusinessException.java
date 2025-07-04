package br.com.kaindall.products.domain.models.exceptions;

import java.time.LocalDateTime;

public class BusinessException extends RuntimeException {
    private final int code;
    private final LocalDateTime timestamp;

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public int getCode() {return this.code;}
    public LocalDateTime getTimestamp() {return this.timestamp;}

}
