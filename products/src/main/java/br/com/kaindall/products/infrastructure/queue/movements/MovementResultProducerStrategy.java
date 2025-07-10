package br.com.kaindall.products.infrastructure.queue.movements;

import br.com.kaindall.products.infrastructure.queue.movements.entities.MovementResultMessage;

public interface MovementResultProducerStrategy {
    void notify(MovementResultMessage result);
}
