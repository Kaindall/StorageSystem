package br.com.kaindall.orders.infrastructure.adapters.mappers;

import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.infrastructure.jpa.entities.OrderEntity;
import br.com.kaindall.orders.infrastructure.qeue.producers.notifications.entities.NotificationMessage;
import br.com.kaindall.orders.infrastructure.qeue.producers.products.entities.MovementMessage;
import org.springframework.stereotype.Component;

import java.util.Collections;

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

    public Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getUserId(),
                Collections.emptyList(),
                orderEntity.getStatus(),
                orderEntity.getDate()
        );
    }

    public MovementMessage toMovementMessage(Order createdOrder) {
        return null;
    }

    public NotificationMessage toNotificationEntity(Order createdOrder) {
        return null;
    }
}
