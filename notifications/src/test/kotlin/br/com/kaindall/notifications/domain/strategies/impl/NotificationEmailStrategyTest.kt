package br.com.kaindall.notifications.domain.strategies.impl

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import br.com.kaindall.notifications.domain.entities.User
import br.com.kaindall.notifications.domain.enums.EmailNotificationType
import br.com.kaindall.notifications.domain.ports.EmailNotifier
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
class NotificationEmailStrategyTest {

    @MockK
    lateinit var notifier: EmailNotifier

    @MockK
    lateinit var userFinder: UserFinder

    @InjectMockKs
    lateinit var emailStrategy: NotificationEmailStrategy

    @BeforeEach
    fun setupStatics() {
        MockKAnnotations.init(this)
        mockkObject(EmailNotificationType.Companion)
    }

    @Test
    fun `deve enviar notificação com sucesso`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", null)
        every { EmailNotificationType.fromNotificationType(any()) } returns EmailNotificationType.PRODUCT_SENT
        every { notifier.send(any()) } just Runs

        emailStrategy.send(notification)


        verify (exactly = 1) { userFinder.find(any()) }
        verify (exactly = 1) { notifier.send(any()) }
    }
    @Test
    fun `deve falhar em enviar notificação por erro em buscar o usuário`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } throws RuntimeException("Usuário não encontrado")

        assertThrows<RuntimeException> { emailStrategy.send(notification) }
    }

    @Test
    fun `deve falhar em enviar notificação por erro em converter o tipo da notificação em tipo de email`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", null)
        every { EmailNotificationType.fromNotificationType(any()) } throws IllegalArgumentException()


        assertThrows<IllegalArgumentException> { emailStrategy.send(notification) }
    }

    @Test
    fun `deve falhar em enviar notificação por erro no notificador`(){
        val notification = Notification(123L, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        every { userFinder.find(any()) } returns User("Teste", "teste@email.com", null)
        every { EmailNotificationType.fromNotificationType(any()) } returns EmailNotificationType.PRODUCT_SENT
        every { notifier.send(any()) } throws RuntimeException("Serviço de notificação inválido")


        assertThrows<RuntimeException> { emailStrategy.send(notification) }
    }
}