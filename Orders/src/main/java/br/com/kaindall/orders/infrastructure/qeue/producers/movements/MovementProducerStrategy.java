package br.com.kaindall.orders.infrastructure.qeue.producers.movements;

import br.com.kaindall.orders.infrastructure.qeue.producers.movements.entities.MovementMessage;

public interface MovementProducerStrategy {
    void sendMessage(MovementMessage message);
}
