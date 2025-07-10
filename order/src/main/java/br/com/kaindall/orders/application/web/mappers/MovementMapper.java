package br.com.kaindall.orders.application.web.mappers;

import br.com.kaindall.orders.application.web.dtos.request.MovementRequestDTO;
import br.com.kaindall.orders.application.web.dtos.response.MovementResponseDTO;
import br.com.kaindall.orders.domain.movements.entities.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public Movement toDomain(MovementRequestDTO movement) {
        return new Movement(
                null,
                movement.productId(),
                null,
                movement.quantity(),
                movement.flow()
        );
    }

    public MovementResponseDTO toDTO(Movement movement) {
        return new MovementResponseDTO(
                movement.productId(),
                movement.orderId(),
                movement.quantity(),
                movement.flow()
        );
    }

}
