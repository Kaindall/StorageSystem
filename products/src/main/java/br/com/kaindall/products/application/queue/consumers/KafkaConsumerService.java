package br.com.kaindall.products.application.queue.consumers;

import br.com.kaindall.products.application.queue.dtos.MovementEvent;
import br.com.kaindall.products.application.queue.mappers.MovementEventMapper;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.product.entities.exceptions.ProductNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnavailableProductQuantityException;
import br.com.kaindall.products.domain.product.facades.ProductFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumerService {
    ProductFacade productFacade;
    MovementEventMapper movementMapper;

    KafkaConsumerService(ProductFacade productFacade, MovementEventMapper movementMapper) {
        this.productFacade = productFacade;
        this.movementMapper = movementMapper;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaMovementListenerFactory")
    public void listen(MovementEvent event) {
        try {
            Movement movement = movementMapper.toDomain(event, (productFacade.find(event.productId())));
            Movement createdMovement = productFacade.processMovement(movement);
            productFacade.emitResult(createdMovement);

        } catch (ProductNotFoundException exception) {
            productFacade.emitResult(
                    event.orderId(),
                    new ProductNotFoundException(event.productId(), Map.of(
                            "orderId", event.orderId(),
                            "productId", event.productId()
            )));
        } catch (UnavailableProductQuantityException exception){
            productFacade.emitResult(
                    event.orderId(),
                    new UnavailableProductQuantityException(Map.of(
                            "orderId", event.orderId(),
                            "productId", event.productId()
            )));
        }
    }
}
