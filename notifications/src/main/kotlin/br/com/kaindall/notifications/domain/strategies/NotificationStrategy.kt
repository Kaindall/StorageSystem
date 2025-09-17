package br.com.kaindall.notifications.domain.strategies

import br.com.kaindall.notifications.domain.entities.Notification

interface NotificationStrategy {
    fun send(notification: Notification)
}
