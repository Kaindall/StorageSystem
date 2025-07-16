package br.com.kaindall.products.application.queue.dtos;

import br.com.kaindall.products.domain.movement.utils.enums.MovementType;

import java.time.LocalDateTime;

public record MovementEvent(
        Long productId,
        Long orderId,
        Integer quantity,
        MovementType flow,
        LocalDateTime date
) {
}
