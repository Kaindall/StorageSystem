package br.com.kaindall.orders.application.dtos.request;

import br.com.kaindall.orders.domain.utils.enums.Flow;

public record MovementRequestDTO(
        Long productId,
        Integer quantity,
        Flow flow
) {
}
