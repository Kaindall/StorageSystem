package br.com.kaindall.products.domain.movement.gateways;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.entities.Movement;

public interface MovementResultGateway {
    void publish(Movement movement);
    void publishFail(BusinessException exception);
}
