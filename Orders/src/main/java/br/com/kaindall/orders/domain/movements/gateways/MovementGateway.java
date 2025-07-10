package br.com.kaindall.orders.domain.movements.gateways;

import br.com.kaindall.orders.domain.movements.entities.Movement;

import java.util.List;

public interface MovementGateway {
    void batchAdd(List<Movement> movements);
}
