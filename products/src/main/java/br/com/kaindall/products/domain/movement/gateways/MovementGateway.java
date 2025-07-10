package br.com.kaindall.products.domain.movement.gateways;

import br.com.kaindall.products.domain.movement.entities.Movement;

import java.util.List;

public interface MovementGateway {
    Movement save(Movement movement);

    List<Movement> findAll(Long orderId);

    void saveAll(List<Movement> movements);
}
