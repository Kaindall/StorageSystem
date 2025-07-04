package br.com.kaindall.orders.domain.entities;

import br.com.kaindall.orders.domain.utils.enums.Flow;

public record Movement(
        Long productId,
        Long orderId,
        Integer quantity,
        Flow flow
) {
}
