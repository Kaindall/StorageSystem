package br.com.kaindall.notifications.adapters.queue.consumers

import br.com.kaindall.notifications.adapters.queue.dtos.NotificationEvent
import br.com.kaindall.notifications.adapters.queue.dtos.toDomain
import br.com.kaindall.notifications.domain.services.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaNotificationConsumer(private val notificationService: NotificationService) {
    private val logger = LoggerFactory.getLogger(KafkaNotificationConsumer::class.java)

    @KafkaListener(topics = ["\${kafka.consumer.topic}"], containerFactory = "kafkaNotificationListenerFactory")
    fun listen(event: NotificationEvent) {
        notificationService.send(event.toDomain())
        logger.info("Mensagem processada com sucesso!")
    }
}