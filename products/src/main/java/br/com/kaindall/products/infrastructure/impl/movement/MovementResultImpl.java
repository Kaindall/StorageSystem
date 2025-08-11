package br.com.kaindall.products.infrastructure.impl.movement;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.gateways.MovementResultGateway;
import br.com.kaindall.products.domain.product.entities.exceptions.ProductNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnavailableProductQuantityException;
import br.com.kaindall.products.infrastructure.queue.movements.MovementResultProducerStrategy;
import br.com.kaindall.products.infrastructure.queue.movements.entities.MovementResultMessage;
import br.com.kaindall.products.infrastructure.queue.movements.utils.MovementResultStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MovementResultImpl implements MovementResultGateway {
    private final MovementResultProducerStrategy movementResultProducer;

    MovementResultImpl(MovementResultProducerStrategy movementResultProducer) {
        this.movementResultProducer = movementResultProducer;
    }

    @Override
    public void publish(Movement movement) {
        MovementResultStatus status = MovementResultStatus.SUCCESS;
        movementResultProducer.notify(new MovementResultMessage(
                movement.movementId(),
                movement.orderId(),
                movement.product().id(),
                status,
                status.getDescription()
        ));
    }

    @Override
    public void invalidate(BusinessException exception) {
        Map<String, Object> metadata = exception.getMetadata();
        Long productId = metadata.containsKey("productId") ? (Long) metadata.get("productId") : null;
        Long orderId = metadata.containsKey("orderId") ? (Long) metadata.get("orderId") : null;
        MovementResultStatus status;
        switch (exception) {
            case ProductNotFoundException e -> status = MovementResultStatus.UNKNOWN_PRODUCT;
            case UnavailableProductQuantityException e -> status = MovementResultStatus.INVALID_QUANTITY;
            default -> status = MovementResultStatus.FAIL;
        }
        movementResultProducer.notify(new MovementResultMessage(
                null,
                orderId,
                productId,
                status,
                status.getDescription()
        ));
    }
}
