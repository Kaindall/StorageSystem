package br.com.kaindall.orders.application.web.dtos.response;

import br.com.kaindall.orders.domain.movements.services.Flow;

public record MovementResponseDTO(
        Long movementId,
        Long productId,
        Integer quantity,
        Flow flow
) {
}
