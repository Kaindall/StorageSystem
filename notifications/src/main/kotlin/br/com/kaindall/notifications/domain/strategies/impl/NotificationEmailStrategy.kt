package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Email
import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.User
import br.com.kaindall.notifications.domain.enums.EmailNotificationType
import br.com.kaindall.notifications.domain.ports.EmailNotifier
import br.com.kaindall.notifications.domain.ports.UserFinder
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.springframework.stereotype.Component

@Component()
class NotificationEmailStrategy(
    private val notifier: EmailNotifier,
    private val userFinder: UserFinder,
) : NotificationStrategy {
    override fun send(notification: Notification) {
        val user: User = userFinder.find(notification.userId)
        val emailNotificationType = EmailNotificationType.fromNotificationType(notification.type)

        notifier.send(Email(
            destiniesToSend = arrayOf(user.email),
            subject = emailNotificationType.subject,
            body = emailNotificationType.body,
        ))
    }
}