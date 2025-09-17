package br.com.kaindall.notifications.domain.services

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import br.com.kaindall.notifications.domain.factories.NotificationFactory
import br.com.kaindall.notifications.domain.strategies.impl.NotificationEmailStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationSmsStrategy
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class NotificationServiceTest {

    @MockK
    lateinit var notificationFactory: NotificationFactory

    @MockK
    lateinit var notificationEmailStrategy: NotificationEmailStrategy

    @MockK
    lateinit var notificationSmsStrategy: NotificationSmsStrategy

    @InjectMockKs
    lateinit var notificationService: NotificationService

    @BeforeEach
    fun setup() {
        every { notificationFactory.create(NotificationChannel.EMAIL) } returns notificationEmailStrategy
        every { notificationFactory.create(NotificationChannel.SMS) } returns notificationSmsStrategy
    }

    @Test
    fun `deve enviar a notificação de EMAIL com sucesso`(){
        val notification = Notification(123, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { notificationEmailStrategy.send(notification) } just runs

        notificationService.send(notification)

        verify (exactly = 0) {notificationSmsStrategy.send(notification)}
        verify (exactly = 1) {notificationEmailStrategy.send(notification)}
    }

    @Test
    fun `deve enviar a notificação de SMS com sucesso`(){
        val notification = Notification(123, NotificationChannel.SMS, NotificationType.PRODUCT_SENT)
        every { notificationSmsStrategy.send(notification) } just runs

        notificationService.send(notification)

        verify (exactly = 0) {notificationEmailStrategy.send(notification)}
        verify (exactly = 1) {notificationSmsStrategy.send(notification)}
    }

    @Test
    fun `deve falhar em enviar notificação com canal inválido`(){
        assertThrows<IllegalArgumentException> {
            notificationService.send(Notification(
                123,
                NotificationChannel.valueOf("INVALIDO"),
                NotificationType.PRODUCT_SENT)) }
    }

    @Test
    fun `deve falhar em enviar notificação com tipo inválido`(){
        assertThrows<IllegalArgumentException> {
            notificationService.send(Notification(
                123,
                NotificationChannel.EMAIL,
                NotificationType.valueOf("INVALIDO"))) }
    }

    @Test
    fun `deve retornar erro por serviço de notificação inválido`(){
        val notification = Notification(123, NotificationChannel.SMS, NotificationType.PRODUCT_SENT)
        every { notificationSmsStrategy.send(any()) } throws RuntimeException("Serviço inválido")

        assertThrows<RuntimeException> { notificationService.send(notification) }
    }
}