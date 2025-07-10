package br.com.kaindall.products.infrastructure.qeue.movements.entities;

import br.com.kaindall.products.infrastructure.qeue.movements.utils.MovementResultStatus;

public record MovementResultMessage(
        Long movementId,
        Long orderId,
        Long productId,
        MovementResultStatus status,
        String message
) {
}
