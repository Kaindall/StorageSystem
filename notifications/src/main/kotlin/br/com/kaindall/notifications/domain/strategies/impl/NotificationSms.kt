package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.springframework.stereotype.Component

@Component("SmsStrategy")
class NotificationSms(private val notifier: SmsNotifier) : NotificationStrategy {
    override fun send(notification: Notification) {
        notifier.send(notification)
    }
}