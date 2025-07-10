package br.com.kaindall.orders.infrastructure.adapters.orders;

import br.com.kaindall.orders.domain.movements.entities.Movement;
import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.domain.orders.entities.OrdersPage;
import br.com.kaindall.orders.domain.orders.gateways.OrderGateway;
import br.com.kaindall.orders.infrastructure.adapters.movements.mappers.MovementClientMapper;
import br.com.kaindall.orders.infrastructure.adapters.orders.mappers.OrderEntityMapper;
import br.com.kaindall.orders.infrastructure.clients.movements.MovementsClient;
import br.com.kaindall.orders.infrastructure.clients.movements.dtos.MovementClientResponseDTO;
import br.com.kaindall.orders.infrastructure.jpa.orders.entities.OrderEntity;
import br.com.kaindall.orders.infrastructure.jpa.orders.repositories.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAdapter implements OrderGateway {
    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final MovementsClient movementsClient;
    private final MovementClientMapper movementClientMapper;

    OrderAdapter (OrderRepository repository,
                  OrderEntityMapper orderEntityMapper, MovementsClient movementsClient, MovementClientMapper movementClientMapper) {
        this.orderRepository = repository;
        this.orderEntityMapper = orderEntityMapper;
        this.movementsClient = movementsClient;
        this.movementClientMapper = movementClientMapper;
    }

    @Override
    public Order add(Order order) {
        OrderEntity createdOrder = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toDomain(createdOrder, order.movements());
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
                .map(orderEntity -> {
                    List<Movement> movements = movementsClient
                            .getAllMovementsByOrderId(orderEntity.getOrderId())
                            .stream()
                            .map(movementClientMapper::toDomain)
                            .toList();
                    return orderEntityMapper.toDomain(orderEntity, movements);
                })
                .toList();
    }

    @Override
    public Order find(Long orderId) {
        return orderEntityMapper.toDomain(orderRepository.findById(orderId).get());
    }

    @Override
    public void update(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
