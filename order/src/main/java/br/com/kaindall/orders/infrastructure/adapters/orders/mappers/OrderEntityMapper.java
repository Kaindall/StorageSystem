package br.com.kaindall.orders.infrastructure.adapters.orders.mappers;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.infrastructure.jpa.orders.entities.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEntityMapper {
    public OrderEntity toEntity(Order order) {
        return new OrderEntity(
            order.id(),
            order.userId(),
            order.status(),
            order.date()
        );
    }

    public Order toDomain(OrderEntity orderEntity, List<Movement> movements) {
        return new Order(
                orderEntity.getOrderId(),
                orderEntity.getUserId(),
                movements
                        .stream()
                        .map(movement -> new Movement(
                                movement.movementId(),
                                movement.productId(),
                                orderEntity.getOrderId(),
                                movement.quantity(),
                                movement.flow()))
                        .toList(),
                orderEntity.getStatus(),
                orderEntity.getDate()
        );
    }

    public Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getOrderId(),
                orderEntity.getUserId(),
                null,
                orderEntity.getStatus(),
                orderEntity.getDate()
        );
    }
}
