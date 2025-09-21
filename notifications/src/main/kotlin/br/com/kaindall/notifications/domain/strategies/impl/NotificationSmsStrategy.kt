package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.Sms
import br.com.kaindall.notifications.domain.enums.SmsNotificationType
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import br.com.kaindall.notifications.domain.ports.UserFinder
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("SmsStrategy")
class NotificationSmsStrategy(
    private val notifier: SmsNotifier,
    private val userFinder: UserFinder
) : NotificationStrategy {
    private val logger = LoggerFactory.getLogger(NotificationSmsStrategy::class.java)

    override fun send(notification: Notification) {
        logger.info("Notificação via SMS inicializada")
        val user = userFinder.find(notification.userId)
        val smsNotificationType = SmsNotificationType.fromNotificationType(notification.type)

        if (user.phoneNumber != null) {
            notifier.send(Sms(user.phoneNumber, smsNotificationType.body))
        } else {
            logger.error("Usuário sem telefone informado")
        }
    }
}