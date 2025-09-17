package br.com.kaindall.notifications.adapters.queue.consumers

import br.com.kaindall.notifications.adapters.queue.dtos.NotificationEvent
import br.com.kaindall.notifications.adapters.queue.dtos.toDomain
import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import br.com.kaindall.notifications.domain.services.NotificationService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class KafkaNotificationConsumerTest {

    @MockK(relaxed = true)
    lateinit var notificationService: NotificationService

    @InjectMockKs
    lateinit var kafkaNotificationConsumer: KafkaNotificationConsumer

    @Test
    fun `deve chamar NotificationService com dominio convertido com sucesso`(){
        val event = NotificationEvent(1, NotificationChannel.EMAIL, NotificationType.PAYMENT_PENDING)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve falhar em chamar NotificationService com userId negativo`(){
        val event = NotificationEvent(-1, NotificationChannel.EMAIL, NotificationType.PAYMENT_PENDING)

        assertThrows<IllegalArgumentException> { kafkaNotificationConsumer.listen(event) }
    }

    @Test
    fun `deve chamar NotificationService com canal EMAIL e tipo PAYMENT_PENDING`(){
        val event = NotificationEvent(1, NotificationChannel.EMAIL, NotificationType.PAYMENT_PENDING)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal EMAIL e tipo PAYMENT_CONFIRMED`(){
        val event = NotificationEvent(1, NotificationChannel.EMAIL, NotificationType.PAYMENT_CONFIRMED)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal EMAIL e tipo PRODUCT_DELIVERING`(){
        val event = NotificationEvent(1, NotificationChannel.EMAIL, NotificationType.PRODUCT_DELIVERING)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal EMAIL e tipo PRODUCT_SENT`(){
        val event = NotificationEvent(1, NotificationChannel.EMAIL, NotificationType.PRODUCT_SENT)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal SMS e tipo PAYMENT_PENDING`(){
        val event = NotificationEvent(1, NotificationChannel.SMS, NotificationType.PAYMENT_PENDING)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal SMS e tipo PAYMENT_CONFIRMED`(){
        val event = NotificationEvent(1, NotificationChannel.SMS, NotificationType.PAYMENT_CONFIRMED)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal SMS e tipo PRODUCT_DELIVERING`(){
        val event = NotificationEvent(1, NotificationChannel.SMS, NotificationType.PRODUCT_DELIVERING)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }

    @Test
    fun `deve chamar NotificationService com canal SMS e tipo PRODUCT_SENT`(){
        val event = NotificationEvent(1, NotificationChannel.SMS, NotificationType.PRODUCT_SENT)
        val expectedNotification = event.toDomain()

        kafkaNotificationConsumer.listen(event)

        verify (exactly = 1) {notificationService.send(expectedNotification)}
    }
}