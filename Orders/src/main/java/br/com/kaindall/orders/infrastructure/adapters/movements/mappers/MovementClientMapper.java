package br.com.kaindall.orders.infrastructure.adapters.movements.mappers;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.infrastructure.clients.movements.dtos.MovementClientResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MovementClientMapper {
    public Movement toDomain(MovementClientResponseDTO movementClientResponseDTO) {
        return new Movement(
                movementClientResponseDTO.movementId(),
                movementClientResponseDTO.productId(),
                movementClientResponseDTO.orderId(),
                movementClientResponseDTO.quantity(),
                movementClientResponseDTO.flow()
        );
    }
}
