package br.com.kaindall.orders.application.qeue.dtos;

public record MovementResultEvent(
        Long movementId,
        Long orderId,
        Long productId,
        MovementResultStatus status,
        String message
) {
}
