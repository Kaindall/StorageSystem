package br.com.kaindall.notifications.domain.strategies

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.NotificationType
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

interface NotificationStrategy {
    fun send(notification: Notification)
}

@Service
class NotificationFactory (
    @Qualifier("SmsStrategy") private val smsNotifier: NotificationStrategy,
    @Qualifier("EmailStrategy") private val emailNotifier: NotificationStrategy,
) {
    fun create(notificationType: NotificationType): NotificationStrategy =
        when (notificationType) {
            NotificationType.EMAIL -> emailNotifier
            NotificationType.SMS -> smsNotifier
        }
}