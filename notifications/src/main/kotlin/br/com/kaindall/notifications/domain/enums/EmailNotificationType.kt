package br.com.kaindall.notifications.domain.enums

import br.com.kaindall.notifications.domain.entities.NotificationType

enum class EmailNotificationType(
    val notificationType: NotificationType,
    val subject: String,
    val body: String
) {
    PAYMENT_PENDING(
        notificationType = NotificationType.PAYMENT_PENDING,
        subject = "Pagamento pendente",
        body = "O pagamento está pendente"
    ),
    PAYMENT_CONFIRMED(
        notificationType = NotificationType.PAYMENT_CONFIRMED,
        subject = "Pagamento confirmado",
        body = "O pagamento foi confirmado"
    ),
    PRODUCT_DELIVERING(
        notificationType = NotificationType.PRODUCT_DELIVERING,
        subject = "Produto a caminho",
        body = "O produto está a caminho"
    ),
    PRODUCT_SENT(
        notificationType = NotificationType.PRODUCT_SENT,
        subject = "Produto entregue",
        body = "O produto foi entregue"
    );

    companion object {
        fun fromNotificationType(type: NotificationType): EmailNotificationType {
            return entries.first { it.notificationType == type }
        }
    }
}
