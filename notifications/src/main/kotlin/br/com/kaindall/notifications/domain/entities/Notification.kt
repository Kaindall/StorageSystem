package br.com.kaindall.notifications.domain.entities

data class Notification(
    val userId: Long,
    val channel: NotificationChannel,
    val type: NotificationType,
) {
    init {
        require(userId >= 0) { "Id não pode ser negativo: $userId" }
    }
}

enum class NotificationChannel {
    EMAIL, SMS
}

enum class NotificationType {
    PAYMENT_PENDING, PAYMENT_CONFIRMED, PRODUCT_DELIVERING, PRODUCT_SENT
}
