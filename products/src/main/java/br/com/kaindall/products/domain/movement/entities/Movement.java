package br.com.kaindall.products.domain.movement.entities;

import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Movement(
        Long movementId,
        Long orderId,
        Product product,
        MovementType type,
        int quantity,
        BigDecimal price,
        LocalDateTime date
) {
}
