package br.com.kaindall.notifications.adapters.queue.dtos

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.utils.NotificationType

data class NotificationEvent(
    val userId: Long,
    val notificationType: NotificationType,
    val message: String,
)

fun NotificationEvent.toDomain(): Notification {
    return Notification(
        id = null,
        userId = userId,
        type = notificationType,
        message = message,
    )
}