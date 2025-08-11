package br.com.kaindall.orders.application.queue.consumers;

import br.com.kaindall.orders.application.queue.dtos.MovementResultEvent;
import br.com.kaindall.orders.application.queue.mappers.MovementStatusMapper;
import br.com.kaindall.orders.domain.orders.facade.OrderFacade;
import br.com.kaindall.orders.domain.orders.services.OrderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMovementsConsumer {
    private final OrderService orderService;
    private final MovementStatusMapper movementStatusMapper;

    public KafkaMovementsConsumer(OrderService orderService, MovementStatusMapper movementStatusMapper) {
        this.orderService = orderService;
        this.movementStatusMapper = movementStatusMapper;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaMovementListenerFactory")
    public void listen(MovementResultEvent event) {
        orderService.updateStatus(event.orderId(), movementStatusMapper.toOrderStatus(event.status()));
    }
}
