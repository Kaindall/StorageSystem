package br.com.kaindall.notifications.domain.factories

import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.strategies.impl.NotificationEmailStrategy
import br.com.kaindall.notifications.domain.strategies.impl.NotificationSmsStrategy
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class NotificationFactoryTest {

    @MockK
    lateinit var notificationEmailStrategy: NotificationEmailStrategy

    @MockK
    lateinit var notificationSmsStrategy: NotificationSmsStrategy

    @InjectMockKs
    lateinit var notificationFactory: NotificationFactory

    @Test
    fun `deve retornar EmailStrategy com sucesso`(){
        val result = notificationFactory.create(NotificationChannel.EMAIL)

        assertEquals(notificationEmailStrategy, result)
    }

    @Test
    fun `deve retornar SmsStrategy com sucesso`(){
        val result = notificationFactory.create(NotificationChannel.SMS)

        assertEquals(notificationSmsStrategy, result)
    }

    @Test
    fun `deve retornar erro por conta de canal inválido`(){
        assertThrows<IllegalArgumentException> {
            notificationFactory.create(NotificationChannel.valueOf("INVALIDO"))
        }
    }
}