package br.com.kaindall.products.domain.movement;

import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.services.MovementService;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.services.ProductService;
import org.springframework.stereotype.Component;

@Component
public class MovementCancel implements MovementInterface {

    private final MovementService movementService;

    public MovementCancel(MovementService movementService) {
        this.movementService = movementService;
    }

    @Override
    public Movement execute(Movement movement) {
        movementService.cancel(movement);

        // TODO corrigir
        return movement;
    }
}
