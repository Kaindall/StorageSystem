package br.com.kaindall.orders.application.queue.dtos;

public record MovementResultEvent(
        Long movementId,
        Long orderId,
        Long productId,
        MovementResultStatus status,
        String message
) {
}
