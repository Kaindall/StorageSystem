package br.com.kaindall.products.application.web.dtos.requests;

import java.math.BigDecimal;

public record CreateProductDTO(
        String name,
        String description,
        String categoryName,
        BigDecimal price,
        int quantity
) {
}
