package br.com.kaindall.orders.domain.services;

import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.domain.entities.OrdersPage;
import br.com.kaindall.orders.domain.gateways.OrderGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderGateway orderGateway;

    OrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order addOrder(Order order) {
        return orderGateway.add(order);
    }

    public List<Order> batchRetrieveByUser(Long userId, OrdersPage ordersPage) {
        return orderGateway.findAll(userId, ordersPage);
    }
}
