package br.com.kaindall.orders.application.mappers;

import br.com.kaindall.orders.application.dtos.request.MovementRequestDTO;
import br.com.kaindall.orders.domain.entities.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public Movement toDomain(MovementRequestDTO movement) {
        return new Movement(
                movement.productId(),
                null,
                movement.quantity(),
                movement.flow()
        );
    }
}
