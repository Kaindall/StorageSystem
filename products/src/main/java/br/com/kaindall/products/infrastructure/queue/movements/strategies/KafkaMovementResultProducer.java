package br.com.kaindall.products.infrastructure.queue.movements.strategies;

import br.com.kaindall.products.infrastructure.queue.movements.MovementResultProducerStrategy;
import br.com.kaindall.products.infrastructure.queue.movements.entities.MovementResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMovementResultProducer implements MovementResultProducerStrategy {
    private final String TOPIC;
    private final KafkaTemplate<String, MovementResultMessage> kafkaTemplate;

    public KafkaMovementResultProducer(KafkaTemplate<String, MovementResultMessage> kafkaTemplate,
                                 @Value("${kafka.producer.topic}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.TOPIC = topicName;
    }

    @Override
    public void notify(MovementResultMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
