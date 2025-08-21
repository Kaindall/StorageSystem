package br.com.kaindall.products.application.web.mappers;

import br.com.kaindall.products.application.web.dtos.responses.MovementDTO;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.product.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public MovementDTO toDTO(Movement movement) {
        return new MovementDTO(
                movement.movementId(),
                movement.orderId(),
                movement.product().id(),
                movement.type(),
                movement.quantity(),
                movement.price(),
                movement.date()
        );
    }
}
