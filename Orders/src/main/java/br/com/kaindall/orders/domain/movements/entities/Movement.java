package br.com.kaindall.orders.domain.movements.entities;

import br.com.kaindall.orders.domain.movements.services.Flow;

public record Movement(
        Long movementId,
        Long productId,
        Long orderId,
        Integer quantity,
        Flow flow
) {
}
