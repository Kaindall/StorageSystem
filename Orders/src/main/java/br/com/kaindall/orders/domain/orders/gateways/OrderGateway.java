package br.com.kaindall.orders.domain.orders.gateways;

import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.domain.orders.entities.OrdersPage;

import java.util.List;

public interface OrderGateway {
    public Order add (Order order);

    List<Order> findAll(Long userId, OrdersPage ordersPage);

    Order find(Long orderId);

    void update(Order order);
}
