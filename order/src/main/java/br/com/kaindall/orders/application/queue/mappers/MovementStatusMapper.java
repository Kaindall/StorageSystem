package br.com.kaindall.orders.application.queue.mappers;

import br.com.kaindall.orders.application.queue.dtos.MovementResultStatus;
import br.com.kaindall.orders.domain.orders.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class MovementStatusMapper {
    public OrderStatus toOrderStatus(MovementResultStatus movementStatus) {
        OrderStatus orderStatus;
        switch (movementStatus) {
            case MovementResultStatus.SUCCESS -> {
                orderStatus = OrderStatus.COMPLETED;
            }
            case MovementResultStatus.UNKNOWN_PRODUCT,
                 MovementResultStatus.INVALID_QUANTITY,
                 MovementResultStatus.FAIL -> {
                orderStatus = OrderStatus.INVALID;
            }
            default -> orderStatus = OrderStatus.PROCESSING;
        }
        return orderStatus;
    }
}
