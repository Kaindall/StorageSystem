package br.com.kaindall.products.application.web.dtos.responses;

import java.time.LocalDateTime;

public record ErrorDTO (
        int code,
        String message,
        LocalDateTime timestamp
) {
}
