package br.com.kaindall.orders.infrastructure.queue.producers.notifications;

import br.com.kaindall.orders.infrastructure.queue.producers.notifications.entities.NotificationMessage;
import org.springframework.stereotype.Service;

@Service
public interface NotificationProducerStrategy {
    public void sendMessage(NotificationMessage message);
}
