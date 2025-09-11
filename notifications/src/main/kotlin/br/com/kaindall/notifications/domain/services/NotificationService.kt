package br.com.kaindall.notifications.domain.services

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.strategies.NotificationFactory
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val factory: NotificationFactory
) {
    fun send(notification: Notification) {
        val notifier: NotificationStrategy = factory.create(notification.type)
        notifier.send(notification)
    }
}