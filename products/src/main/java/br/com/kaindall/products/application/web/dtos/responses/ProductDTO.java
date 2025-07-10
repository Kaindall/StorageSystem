package br.com.kaindall.products.application.web.dtos.responses;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price,
        int quantity
) {
}
