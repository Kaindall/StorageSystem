package br.com.kaindall.notifications.domain.entities

data class Notification(
    var id: Long?,
    val userId: Long,
    val type: NotificationType,
    val message: String,
)

enum class NotificationType {
    EMAIL, SMS
}


