package br.com.kaindall.products.application.web.dtos.responses;

import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.product.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementDTO(
        Long movementId,
        Long orderId,
        Long productId,
        MovementType type,
        int quantity,
        BigDecimal price,
        LocalDateTime date
) {
}
