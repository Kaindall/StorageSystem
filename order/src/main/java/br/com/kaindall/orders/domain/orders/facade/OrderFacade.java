package br.com.kaindall.orders.domain.orders.facade;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.domain.movements.services.MovementService;
import br.com.kaindall.orders.domain.notifications.services.NotificationService;
import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.domain.orders.entities.OrderStatus;
import br.com.kaindall.orders.domain.orders.entities.OrdersPage;
import br.com.kaindall.orders.domain.orders.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFacade {
    private final OrderService orderService;
    private final MovementService movementService;
    private final NotificationService notificationService;

    public OrderFacade(OrderService orderService, MovementService movementService, NotificationService notificationService) {
        this.orderService = orderService;
        this.movementService = movementService;
        this.notificationService = notificationService;
    }

    public Order add(Order order) {
        Order createdOrder = orderService.add(order);
        movementService.batchAdd(order
                .movements()
                .stream()
                .map(movement -> new Movement(
                        movement.movementId(),
                        movement.productId(),
                        createdOrder.id(),
                        movement.quantity(),
                        movement.flow()))
                .toList());
        return createdOrder;
    }

    public List<Order> pagedQueryByUser(Long userId, OrdersPage ordersPage) {
        return orderService.batchRetrieveByUser(userId, ordersPage);
    }

    public Order find(Long orderId) {
        return orderService.find(orderId);
    }

    public void update(Order order) {
        orderService.update(order);
    }

    public void updateStatus(Long orderId, OrderStatus orderStatus) {
        orderService.updateStatus(orderId, orderStatus);
    }
}
