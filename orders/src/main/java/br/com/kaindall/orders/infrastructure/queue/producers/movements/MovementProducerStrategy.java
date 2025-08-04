package br.com.kaindall.orders.infrastructure.queue.producers.movements;

import br.com.kaindall.orders.infrastructure.queue.producers.movements.entities.MovementMessage;

public interface MovementProducerStrategy {
    void sendMessage(MovementMessage message);
}
