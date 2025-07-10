package br.com.kaindall.orders.infrastructure.queue.producers.movements.entities;

import br.com.kaindall.orders.domain.movements.services.Flow;

import java.time.LocalDateTime;

public record MovementMessage(
        Long productId,
        Long orderId,
        Integer quantity,
        Flow flow,
        LocalDateTime date
) {
}
