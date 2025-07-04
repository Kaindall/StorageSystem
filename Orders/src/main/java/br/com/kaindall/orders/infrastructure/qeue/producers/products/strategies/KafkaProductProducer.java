package br.com.kaindall.orders.infrastructure.qeue.producers.products.strategies;

import br.com.kaindall.orders.infrastructure.qeue.producers.products.ProductProducerStrategy;
import br.com.kaindall.orders.infrastructure.qeue.producers.products.entities.MovementMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProductProducer implements ProductProducerStrategy {
    private final KafkaTemplate<String, MovementMessage> kafkaTemplate;

    public KafkaProductProducer(KafkaTemplate<String, MovementMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(MovementMessage message) {
        kafkaTemplate.send("topic-hello", message);
    }
}
