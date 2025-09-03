package br.com.kaindall.products.domain.movement.strategies.impl;

import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.services.MovementService;
import br.com.kaindall.products.domain.movement.strategies.MovementStrategy;
import org.springframework.stereotype.Component;

@Component
public class MovementCancel implements MovementStrategy {

    private final MovementService movementService;

    public MovementCancel(MovementService movementService) {
        this.movementService = movementService;
    }

    @Override
    public Movement execute(Movement movement) {
        movementService.cancel(movement);
        return movement;
    }
}
