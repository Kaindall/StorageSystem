package br.com.kaindall.orders.application.qeue.consumers;

import br.com.kaindall.orders.application.qeue.dtos.MovementResultEvent;
import br.com.kaindall.orders.application.qeue.mappers.MovementStatusMapper;
import br.com.kaindall.orders.domain.orders.facade.OrderFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMovementsConsumer {
    private final OrderFacade orderFacade;
    private final MovementStatusMapper movementStatusMapper;

    public KafkaMovementsConsumer(OrderFacade orderFacade, MovementStatusMapper movementStatusMapper) {
        this.orderFacade = orderFacade;
        this.movementStatusMapper = movementStatusMapper;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaMovementListenerFactory")
    public void listen(MovementResultEvent event) {
        orderFacade.updateStatus(event.orderId(), movementStatusMapper.toOrderStatus(event.status()));
    }
}
