package br.com.kaindall.orders.infrastructure.qeue.producers.notifications.strategies;

import br.com.kaindall.orders.infrastructure.qeue.producers.notifications.NotificationProducerStrategy;
import br.com.kaindall.orders.infrastructure.qeue.producers.notifications.entities.NotificationMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationProducer implements NotificationProducerStrategy {
    private static final String TOPIC = "notification-qeue";
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public KafkaNotificationProducer(KafkaTemplate<String, NotificationMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(NotificationMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
