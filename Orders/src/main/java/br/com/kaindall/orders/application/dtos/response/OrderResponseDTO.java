package br.com.kaindall.orders.application.dtos.response;

import br.com.kaindall.orders.domain.utils.enums.OrderStatus;

public record OrderResponseDTO(
        Long id,
        Long userId,
        OrderStatus status,
        java.time.LocalDateTime date
) {
}
