package br.com.kaindall.notifications.domain.factories

import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationEmailStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationSmsStrategy
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class NotificationFactory (
    private val notificationSmsStrategyStrategy: NotificationSmsStrategy,
    private val notificationEmailStrategy: NotificationEmailStrategy,
) {
    private val logger = LoggerFactory.getLogger(NotificationFactory::class.java)

    fun create(notificationChannel: NotificationChannel): NotificationStrategy {
        logger.debug("Selecionando o Strategy de notificação")
        return when (notificationChannel) {
            NotificationChannel.EMAIL -> notificationEmailStrategy
            NotificationChannel.SMS -> notificationSmsStrategyStrategy
        }
    }
}