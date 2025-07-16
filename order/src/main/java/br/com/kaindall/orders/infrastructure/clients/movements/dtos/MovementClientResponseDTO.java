package br.com.kaindall.orders.infrastructure.clients.movements.dtos;

import br.com.kaindall.orders.domain.movements.services.Flow;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovementClientResponseDTO(
        Long movementId,
        Long productId,
        Long orderId,
        Integer quantity,
        @JsonProperty("type") Flow flow
        ) {
}
