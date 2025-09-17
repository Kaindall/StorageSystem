package br.com.kaindall.notifications.adapters.queue.dtos

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import com.fasterxml.jackson.annotation.JsonProperty

data class NotificationEvent(
    val userId: Long,
    @JsonProperty("channel")
    val notificationChannel: NotificationChannel,
    @JsonProperty("type")
    val notificationType: NotificationType,
)

fun NotificationEvent.toDomain(): Notification {
    return Notification(
        userId = userId,
        channel = notificationChannel,
        type = notificationType,
    )
}