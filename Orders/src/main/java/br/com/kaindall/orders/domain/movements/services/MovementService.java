package br.com.kaindall.orders.domain.movements.services;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.domain.movements.gateways.MovementGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {
    MovementGateway movementGateway;

    MovementService(MovementGateway movementGateway) {
        this.movementGateway = movementGateway;
    }

    public void batchAdd(List<Movement> movements) {
        movementGateway.batchAdd(movements);
    }
}
