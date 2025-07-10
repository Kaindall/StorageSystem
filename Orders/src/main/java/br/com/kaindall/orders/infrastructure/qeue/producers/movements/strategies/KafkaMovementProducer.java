package br.com.kaindall.orders.infrastructure.qeue.producers.movements.strategies;

import br.com.kaindall.orders.infrastructure.qeue.producers.movements.MovementProducerStrategy;
import br.com.kaindall.orders.infrastructure.qeue.producers.movements.entities.MovementMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMovementProducer implements MovementProducerStrategy {
    private final String TOPIC;
    private final KafkaTemplate<String, MovementMessage> kafkaTemplate;

    public KafkaMovementProducer(KafkaTemplate<String, MovementMessage> kafkaTemplate,
                                 @Value("${kafka.producer.topic}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.TOPIC = topicName;
    }

    @Override
    public void sendMessage(MovementMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
