package br.com.kaindall.orders.infrastructure.adapters;

import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.domain.entities.OrdersPage;
import br.com.kaindall.orders.domain.gateways.OrderGateway;
import br.com.kaindall.orders.infrastructure.adapters.mappers.OrderEntityMapper;
import br.com.kaindall.orders.infrastructure.jpa.repositories.OrderRepository;
import br.com.kaindall.orders.infrastructure.qeue.producers.notifications.NotificationProducer;
import br.com.kaindall.orders.infrastructure.qeue.producers.products.ProductProducerStrategy;
import br.com.kaindall.orders.infrastructure.qeue.producers.products.entities.MovementMessage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAdapter implements OrderGateway {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final ProductProducerStrategy productProducer;
    private final NotificationProducer notificationProducer;

    OrderAdapter (OrderRepository repository,
                  OrderEntityMapper orderEntityMapper,
                  ProductProducerStrategy productProducer,
                  NotificationProducer notificationProducer) {
        this.orderRepository = repository;
        this.orderEntityMapper = orderEntityMapper;
        this.productProducer = productProducer;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public Order add(Order order) {
        Order createdOrder = orderEntityMapper.toDomain(orderRepository.save(orderEntityMapper.toEntity(order)));
        MovementMessage msg = new MovementMessage("Gustavo");
        productProducer.sendMessage(msg);
        //productProducer.sendMessage(orderEntityMapper.toMovementMessage(createdOrder));
        //productProducer.sendMessage("Hello world!");
        //notificationProducer.sendMessage(orderEntityMapper.toNotificationEntity(createdOrder));
        return createdOrder;
    }

    @Override
    public List<Order> findAll(Long userId, OrdersPage ordersPage) {
        Sort.Direction direction = ordersPage.ascending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageableOrder = PageRequest.of(
                ordersPage.pageNumber(),
                ordersPage.pageSize()
        ).withSort(direction, String.valueOf(ordersPage.sort()));
        return orderRepository
                .findAllByUserId(userId, pageableOrder)
                .map(orderEntityMapper::toDomain)
                .toList();
    }
}
