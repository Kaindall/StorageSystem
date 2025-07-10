package br.com.kaindall.products.domain.product.entities;

import br.com.kaindall.products.domain.category.entities.Category;

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
