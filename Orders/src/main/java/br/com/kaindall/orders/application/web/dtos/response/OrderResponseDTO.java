package br.com.kaindall.orders.application.web.dtos.response;

import br.com.kaindall.orders.domain.orders.entities.OrderStatus;

import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        Long userId,
        List<MovementResponseDTO> movements,
        OrderStatus status,
        java.time.LocalDateTime date
) {
}
