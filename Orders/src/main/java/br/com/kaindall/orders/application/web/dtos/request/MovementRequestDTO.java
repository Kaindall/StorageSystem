package br.com.kaindall.orders.application.web.dtos.request;

import br.com.kaindall.orders.domain.movements.services.Flow;

public record MovementRequestDTO(
        Long productId,
        Integer quantity,
        Flow flow
) {
}
