package br.com.kaindall.orders.domain.orders.services;

import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.domain.orders.entities.OrderStatus;
import br.com.kaindall.orders.domain.orders.entities.OrdersPage;
import br.com.kaindall.orders.domain.orders.gateways.OrderGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderGateway orderGateway;

    OrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order add(Order order) {
        return orderGateway.add(order);
    }

    public List<Order> batchRetrieveByUser(Long userId, OrdersPage ordersPage) {
        return orderGateway.findAll(userId, ordersPage);
    }

    public Order find(Long orderId) {
        return orderGateway.find(orderId);
    }

    public void update(Order order) {
        orderGateway.update(order);
    }

    public void updateStatus(Long orderId, OrderStatus orderStatus) {
        Order currentOrder = find(orderId);
        if (currentOrder.status() == orderStatus) {return;}
        if (currentOrder.status() == OrderStatus.CANCELED || currentOrder.status() == OrderStatus.INVALID) {return;}
        update(new Order(
                currentOrder.id(),
                currentOrder.userId(),
                currentOrder.movements(),
                orderStatus,
                currentOrder.date()
        ));
    }
}
