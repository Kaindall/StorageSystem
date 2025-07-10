package br.com.kaindall.orders.infrastructure.adapters.movements;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.domain.movements.gateways.MovementGateway;
import br.com.kaindall.orders.infrastructure.adapters.movements.mappers.MovementMessageMapper;
import br.com.kaindall.orders.infrastructure.queue.producers.movements.MovementProducerStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementAdapter implements MovementGateway {
    private final MovementProducerStrategy movementProducer;
    private final MovementMessageMapper movementMapper;

    public MovementAdapter(MovementProducerStrategy movementProducer, MovementMessageMapper movementMapper) {
        this.movementProducer = movementProducer;
        this.movementMapper = movementMapper;
    }

    @Override
    public void batchAdd(List<Movement> movements) {
        movements
                .stream()
                .map(movementMapper::toMessage)
                .forEach(movementProducer::sendMessage);
    }
}
