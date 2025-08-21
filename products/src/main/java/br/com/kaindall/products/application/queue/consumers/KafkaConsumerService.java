package br.com.kaindall.products.application.queue.consumers;

import br.com.kaindall.products.application.queue.dtos.MovementEvent;
import br.com.kaindall.products.application.queue.mappers.MovementEventMapper;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.product.entities.exceptions.ProductNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnavailableProductQuantityException;
import br.com.kaindall.products.domain.product.facades.ProductFacade;
import br.com.kaindall.products.domain.product.services.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumerService {
    ProductFacade productFacade;
    ProductService productService;
    MovementEventMapper movementMapper;

    KafkaConsumerService(ProductFacade productFacade, MovementEventMapper movementMapper) {
        this.productFacade = productFacade;
        this.movementMapper = movementMapper;
    }

    @KafkaListener(topics = "${kafka.consumer.topic}", containerFactory = "kafkaMovementListenerFactory")
    public void listen(MovementEvent event) {
        Movement movement = movementMapper.toDomain(event, (productService.find(event.productId())));
        productFacade.processMovement(movement);
    }
}
