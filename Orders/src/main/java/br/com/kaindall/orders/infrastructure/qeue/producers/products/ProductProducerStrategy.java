package br.com.kaindall.orders.infrastructure.qeue.producers.products;

import br.com.kaindall.orders.infrastructure.qeue.producers.products.entities.MovementMessage;

public interface ProductProducerStrategy {
    void sendMessage(MovementMessage message);
}
