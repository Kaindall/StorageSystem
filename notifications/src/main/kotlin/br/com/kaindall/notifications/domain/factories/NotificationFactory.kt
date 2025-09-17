package br.com.kaindall.notifications.domain.factories

import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.strategies.NotificationStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationEmailStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationSmsStrategy
import org.springframework.stereotype.Service


@Service
class NotificationFactory (
    private val notificationSmsStrategyStrategy: NotificationSmsStrategy,
    private val notificationEmailStrategy: NotificationEmailStrategy,
) {
    fun create(notificationChannel: NotificationChannel): NotificationStrategy =
        when (notificationChannel) {
            NotificationChannel.EMAIL -> notificationEmailStrategy
            NotificationChannel.SMS -> notificationSmsStrategyStrategy
        }
}