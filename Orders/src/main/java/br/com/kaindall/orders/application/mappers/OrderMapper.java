package br.com.kaindall.orders.application.mappers;

import br.com.kaindall.orders.application.dtos.request.CreateOrderDTO;
import br.com.kaindall.orders.application.dtos.response.OrderResponseDTO;
import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.domain.utils.enums.OrderStatus;
import br.com.kaindall.orders.domain.entities.OrdersPage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {
    private final MovementMapper movementMapper;

    public OrderMapper(MovementMapper movementMapper) {
        this.movementMapper = movementMapper;
    }

    public Order toDomain(CreateOrderDTO order) {
        return new Order (
                null,
                order.userId(),
                order.items().stream().map(movementMapper::toDomain).toList(),
                OrderStatus.REQUESTED,
                LocalDateTime.now()
        );
    }

    public OrderResponseDTO toDto(Order order) {
        return new OrderResponseDTO(
                order.id(),
                order.userId(),
                order.status(),
                order.date()
        );
    }

    public OrdersPage toPagination(int page, int size, String sort, boolean ascending) {
        return new OrdersPage(
                page,
                size,
                sort,
                ascending
        );
    }
}
