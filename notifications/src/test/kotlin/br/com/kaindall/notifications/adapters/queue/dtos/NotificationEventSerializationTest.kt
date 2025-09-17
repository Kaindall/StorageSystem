package br.com.kaindall.notifications.adapters.queue.dtos

import br.com.kaindall.notifications.domain.entities.NotificationChannel
import br.com.kaindall.notifications.domain.entities.NotificationType
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.KotlinInvalidNullException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class NotificationEventSerializationTest {

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `deve deserializar objeto para Kotlin com sucesso`() {
        val json = """
            {
                "userId": 123,
                "channel": "EMAIL",
                "type": "PAYMENT_PENDING"
            }
        """.trimIndent()
        val expectedResult = NotificationEvent(123, NotificationChannel.EMAIL, NotificationType.PAYMENT_PENDING)

        val result = objectMapper.readValue(json, NotificationEvent::class.java)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `deve falhar em desserializar com canal inexistente`() {
        val json = """
            {
                "userId": 123,
                 "channel": "FUMAÇA",
                 "type": "PAYMENT_PENDING"
            }
        """.trimIndent()

        assertThrows<InvalidFormatException> { objectMapper.readValue(json, NotificationEvent::class.java) }
    }

    @Test
    fun `deve falhar em desserializar com tipo inexistente`() {
        val json = """
            {
                "userId": 123,
                 "channel": "EMAIL",
                 "type": "PRODUCT_TRAVELING"
            }
        """.trimIndent()

        assertThrows<InvalidFormatException> { objectMapper.readValue(json, NotificationEvent::class.java) }
    }

    @Test
    fun `deve falhar em desserializar com propriedade ausente`() {
        val json = """
            {
                "userId": 123,
                 "channel": "EMAIL"
            }
        """.trimIndent()

        assertThrows<KotlinInvalidNullException> { objectMapper.readValue(json, NotificationEvent::class.java) }
    }

    @Test
    fun `deve falhar em desserializar com Json inválido`() {
        val json = """
            {
                "userId": 123,
                 "channel": "EMAIL",
            }
        """.trimIndent()

        assertThrows<JsonParseException> { objectMapper.readValue(json, NotificationEvent::class.java) }
    }
}