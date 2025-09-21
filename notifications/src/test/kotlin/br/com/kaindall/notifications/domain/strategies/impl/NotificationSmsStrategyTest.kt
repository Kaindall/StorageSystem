package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import br.com.kaindall.notifications.domain.entities.User
import br.com.kaindall.notifications.domain.enums.SmsNotificationType
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import br.com.kaindall.notifications.domain.ports.UserFinder
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class NotificationSmsStrategyTest {

    @MockK
    lateinit var notifier: SmsNotifier

    @MockK
    lateinit var userFinder: UserFinder

    @InjectMockKs
    lateinit var smsStrategy: NotificationSmsStrategy

    @BeforeEach
    fun setupStatics() {
        MockKAnnotations.init(this)
        mockkObject(SmsNotificationType.Companion)
    }

    @Test
    fun `deve enviar notificação com sucesso`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", "+1411111111")
        every { SmsNotificationType.fromNotificationType(any()) } returns SmsNotificationType.PRODUCT_SENT
        every { notifier.send(any()) } just Runs

        smsStrategy.send(notification)


        verify (exactly = 1) { userFinder.find(any()) }
        verify (exactly = 1) { notifier.send(any()) }
    }

    @Test
    fun `deve falhar em enviar notificação caso usuario não tenha numero de telefone`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", null)
        every { SmsNotificationType.fromNotificationType(any()) } returns SmsNotificationType.PRODUCT_SENT
        every { notifier.send(any()) } just Runs

        smsStrategy.send(notification)


        verify (exactly = 1) { userFinder.find(any()) }
        verify (exactly = 0) { notifier.send(any()) }
    }

    @Test
    fun `deve falhar em enviar notificação por erro em buscar o usuário`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } throws RuntimeException("Usuário não encontrado")

        assertThrows<RuntimeException> { smsStrategy.send(notification) }
    }

    @Test
    fun `deve falhar em enviar notificação por erro em converter o tipo da notificação em tipo de email`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", null)
        every { SmsNotificationType.fromNotificationType(any()) } throws IllegalArgumentException()


        assertThrows<IllegalArgumentException> { smsStrategy.send(notification) }
    }

    @Test
    fun `deve falhar em enviar notificação por erro no notificador`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", "+1411111111")
        every { SmsNotificationType.fromNotificationType(any()) } returns SmsNotificationType.PRODUCT_SENT
        every { notifier.send(any()) } throws RuntimeException("Serviço de notificação inválido")


        assertThrows<RuntimeException> { smsStrategy.send(notification) }
    }
}