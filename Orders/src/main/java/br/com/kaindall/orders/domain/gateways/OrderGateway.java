package br.com.kaindall.orders.domain.gateways;

import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.domain.entities.OrdersPage;

import java.util.List;

public interface OrderGateway {
    public Order add (Order order);

    List<Order> findAll(Long userId, OrdersPage ordersPage);
}
