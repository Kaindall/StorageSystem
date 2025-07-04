package br.com.kaindall.products.domain.models;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        Category category,
        BigDecimal price,
        Integer quantity
) {
}
