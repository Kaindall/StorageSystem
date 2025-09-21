package br.com.kaindall.notifications.domain.enums

import br.com.kaindall.notifications.domain.entities.NotificationType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SmsNotificationTypeTest {

    @Test
    fun `deve retornar o tipo de notificação do SMS com sucesso para Produto Entregue`(){
        val firstResult = SmsNotificationType.fromNotificationType(NotificationType.PRODUCT_SENT)

        assertEquals(firstResult, SmsNotificationType.PRODUCT_SENT)
        assertEquals(firstResult.body, SmsNotificationType.PRODUCT_SENT.body)
    }

    @Test
    fun `deve retornar o tipo de notificação do SMS com sucesso para Pagamento Pendente`(){
        val firstResult = SmsNotificationType.fromNotificationType(NotificationType.PAYMENT_PENDING)

        assertEquals(firstResult, SmsNotificationType.PAYMENT_PENDING)
        assertEquals(firstResult.body, SmsNotificationType.PAYMENT_PENDING.body)
    }

    @Test
    fun `deve retornar o tipo de notificação do SMS com sucesso para Pagamento Confirmado`(){
        val firstResult = SmsNotificationType.fromNotificationType(NotificationType.PAYMENT_CONFIRMED)

        assertEquals(firstResult, SmsNotificationType.PAYMENT_CONFIRMED)
        assertEquals(firstResult.body, SmsNotificationType.PAYMENT_CONFIRMED.body)
    }

    @Test
    fun `deve retornar o tipo de notificação do SMS com sucesso para Produto a Caminho`(){
        val firstResult = SmsNotificationType.fromNotificationType(NotificationType.PRODUCT_DELIVERING)

        assertEquals(firstResult, SmsNotificationType.PRODUCT_DELIVERING)
        assertEquals(firstResult.body, SmsNotificationType.PRODUCT_DELIVERING.body)
    }

    @Test
    fun `deve lançar exceção quando NotificationType não existir no enum`() {
        assertThrows<IllegalArgumentException> {
            SmsNotificationType.fromNotificationType(NotificationType.valueOf("INVALIDO"))
        }
    }

    @Test
    fun `deve verificar se todos os NotificationType tem correspondencia em SmsNotificationType`() {
        val mapped = SmsNotificationType.entries.map { it.notificationType }.toSet()
        assertEquals(NotificationType.entries.toSet(), mapped)
    }
}