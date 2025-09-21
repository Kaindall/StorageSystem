package br.com.kaindall.notifications.domain.enums

import br.com.kaindall.notifications.domain.entities.NotificationType
import org.slf4j.LoggerFactory

enum class SmsNotificationType(
    val notificationType: NotificationType,
    val body: String
) {
    PAYMENT_PENDING(
        notificationType = NotificationType.PAYMENT_PENDING,
        body = "StorageSystem informa: O pagamento está pendente"
    ),
    PAYMENT_CONFIRMED(
        notificationType = NotificationType.PAYMENT_CONFIRMED,
        body = "StorageSystem informa: O pagamento foi confirmado"
    ),
    PRODUCT_DELIVERING(
        notificationType = NotificationType.PRODUCT_DELIVERING,
        body = "StorageSystem informa: O produto está a caminho"
    ),
    PRODUCT_SENT(
        notificationType = NotificationType.PRODUCT_SENT,
        body = "StorageSystem informa: O produto foi entregue"
    );

    companion object {
        private val logger = LoggerFactory.getLogger(SmsNotificationType::class.java)

        fun fromNotificationType(type: NotificationType): SmsNotificationType {
            logger.debug("Convertendo objeto do tipo {}", type)
            return entries.first { it.notificationType == type }
        }
    }
}
