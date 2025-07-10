package br.com.kaindall.products.domain.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class BusinessException extends RuntimeException {
    private final int code;
    private final LocalDateTime timestamp;
    private final Map<String, Object> metadata;

    public BusinessException(String message, int code, Map<String, Object> metadata) {
        super(message);
        this.code = code;
        this.metadata = metadata;
        this.timestamp = LocalDateTime.now();
    }

    public int getCode() {return this.code;}
    public LocalDateTime getTimestamp() {return this.timestamp;}
    public Map<String, Object> getMetadata() {return metadata;}
}
