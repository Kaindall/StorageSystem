package br.com.kaindall.notifications.domain.enums

import br.com.kaindall.notifications.domain.entities.NotificationType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class EmailNotificationTypeTest {

    @Test
    fun `deve retornar o tipo de notificação do e-mail com sucesso para Produto Entregue`(){
        val firstResult = EmailNotificationType.fromNotificationType(NotificationType.PRODUCT_SENT)

        assertEquals(firstResult, EmailNotificationType.PRODUCT_SENT)
        assertEquals(firstResult.body, EmailNotificationType.PRODUCT_SENT.body)
        assertEquals(firstResult.subject, EmailNotificationType.PRODUCT_SENT.subject)
    }

    @Test
    fun `deve retornar o tipo de notificação do e-mail com sucesso para Pagamento Pendente`(){
        val firstResult = EmailNotificationType.fromNotificationType(NotificationType.PAYMENT_PENDING)

        assertEquals(firstResult, EmailNotificationType.PAYMENT_PENDING)
        assertEquals(firstResult.body, EmailNotificationType.PAYMENT_PENDING.body)
        assertEquals(firstResult.subject, EmailNotificationType.PAYMENT_PENDING.subject)
    }

    @Test
    fun `deve retornar o tipo de notificação do e-mail com sucesso para Pagamento Confirmado`(){
        val firstResult = EmailNotificationType.fromNotificationType(NotificationType.PAYMENT_CONFIRMED)

        assertEquals(firstResult, EmailNotificationType.PAYMENT_CONFIRMED)
        assertEquals(firstResult.body, EmailNotificationType.PAYMENT_CONFIRMED.body)
        assertEquals(firstResult.subject, EmailNotificationType.PAYMENT_CONFIRMED.subject)
    }

    @Test
    fun `deve retornar o tipo de notificação do e-mail com sucesso para Produto a Caminho`(){
        val firstResult = EmailNotificationType.fromNotificationType(NotificationType.PRODUCT_DELIVERING)

        assertEquals(firstResult, EmailNotificationType.PRODUCT_DELIVERING)
        assertEquals(firstResult.body, EmailNotificationType.PRODUCT_DELIVERING.body)
        assertEquals(firstResult.subject, EmailNotificationType.PRODUCT_DELIVERING.subject)
    }

    @Test
    fun `deve lançar exceção quando NotificationType não existir no enum`() {
        assertThrows<IllegalArgumentException> {
            SmsNotificationType.fromNotificationType(NotificationType.valueOf("INVALIDO"))
        }
    }

    @Test
    fun `deve verificar se todos os NotificationType tem correspondencia em EmailNotificationType`() {
        val mapped = SmsNotificationType.entries.map { it.notificationType }.toSet()
        assertEquals(NotificationType.entries.toSet(), mapped)
    }
}