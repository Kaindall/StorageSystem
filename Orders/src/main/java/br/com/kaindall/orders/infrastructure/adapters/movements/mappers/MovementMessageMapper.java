package br.com.kaindall.orders.infrastructure.adapters.movements.mappers;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.infrastructure.qeue.producers.movements.entities.MovementMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovementMessageMapper {
    public MovementMessage toMessage(Movement movement) {
        return new MovementMessage(
            movement.productId(),
            movement.orderId(),
            movement.quantity(),
            movement.flow(),
            LocalDateTime.now()
        );
    }
}
