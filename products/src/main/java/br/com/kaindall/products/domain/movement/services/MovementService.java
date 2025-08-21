package br.com.kaindall.products.domain.movement.services;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.gateways.MovementResultGateway;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.movement.gateways.MovementGateway;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.utils.builders.MovementBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovementService {
    private final MovementGateway movementGateway;
    private final MovementResultGateway movementResultGateway;

    public MovementService(MovementGateway movementGateway, MovementResultGateway movementResultGateway) {
        this.movementGateway = movementGateway;
        this.movementResultGateway = movementResultGateway;
    }

    public Movement add(Product product, int quantity, Long orderId) {
        Movement movement = new MovementBuilder()
                .withOrderId(orderId)
                .withProduct(product)
                .withType(MovementType.IN)
                .withQuantity(quantity)
                .withPrice(product.price().multiply(BigDecimal.valueOf(quantity)))
                .withDate(LocalDateTime.now())
                .build();
        return movementGateway.save(movement);
    }

    public Movement decrease(Product product, int quantity, Long orderId) {
        Movement movement = new MovementBuilder()
                .withOrderId(orderId)
                .withProduct(product)
                .withType(MovementType.OUT)
                .withQuantity(quantity)
                .withPrice(product.price().multiply(BigDecimal.valueOf(quantity)))
                .withDate(LocalDateTime.now())
                .build();
        return movementGateway.save(movement);
    }

    public List<Movement> findAll(Long orderId) {
        return movementGateway.findAll(orderId);
    }


    public void result(Movement movement) {
        movementResultGateway.publish(movement);
    }

    public void invalidate(BusinessException exception) {
        movementResultGateway.invalidate(exception);
    }

    public void cancelAll(List<Movement> movements) {
        movementGateway.saveAll(movements
                .stream()
                .map(movement -> new Movement(
                        null,
                        movement.orderId(),
                        movement.product(),
                        MovementType.CANCELED,
                        movement.quantity(),
                        movement.price(),
                        movement.date()))
                .toList());
    }

    public void cancel(Movement movement) {
        movementGateway.save(new Movement(
                null,
                movement.orderId(),
                movement.product(),
                MovementType.CANCELED,
                movement.quantity(),
                movement.price(),
                movement.date()));
    }
}
