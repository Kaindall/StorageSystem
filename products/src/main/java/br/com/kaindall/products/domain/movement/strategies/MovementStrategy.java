package br.com.kaindall.products.domain.movement.strategies;

import br.com.kaindall.products.domain.movement.entities.Movement;

public interface MovementStrategy {

    Movement execute(Movement movement);

}
