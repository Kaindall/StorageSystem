package br.com.kaindall.notifications.domain.services

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.factories.NotificationFactory
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationFactory: NotificationFactory
) {
    fun send(notification: Notification) {
        val notifier: NotificationStrategy = notificationFactory.create(notification.channel)
        notifier.send(notification)
    }
}