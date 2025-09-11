package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.ports.EmailNotifier
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.springframework.stereotype.Component

@Component("EmailStrategy")
class NotificationEmail(private val notifier: EmailNotifier) : NotificationStrategy {
    override fun send(notification: Notification) {
        notifier.send(notification)
    }
}