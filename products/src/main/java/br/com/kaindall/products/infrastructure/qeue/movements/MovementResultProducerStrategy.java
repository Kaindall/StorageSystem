package br.com.kaindall.products.infrastructure.qeue.movements;

import br.com.kaindall.products.infrastructure.qeue.movements.entities.MovementResultMessage;

public interface MovementResultProducerStrategy {
    void notify(MovementResultMessage result);
}
