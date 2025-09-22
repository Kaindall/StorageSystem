package br.com.kaindall.notifications.adapters.http.sms

import br.com.kaindall.notifications.domain.entities.Sms
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TwilioSenderTest {

    @MockK
    lateinit var mockedMessageCreator: MessageCreator

    private val sender = TwilioSender("sid", "token", "originNumber")

    @BeforeEach
    fun setupStatics(){
        MockKAnnotations.init(this)
        mockkStatic(Twilio::class)
        mockkStatic(Message::class)
    }

    @Test
    fun `deve enviar SMS com sucesso`(){
        val sms = Sms("+1477777777", "Lorem ipsum")
        every { Twilio.init(any<String>(), any<String>()) } just Runs
        every { Message.creator(PhoneNumber(sms.destiny), any<PhoneNumber>(), sms.body) } returns mockedMessageCreator
        every { mockedMessageCreator.create() } returns mockk(relaxed = true)

        sender.send(sms)

        verify (exactly = 1) { Twilio.init(any<String>(), any<String>()) }
        verify (exactly = 1) { Message.creator(PhoneNumber(sms.destiny), any<PhoneNumber>(), sms.body) }
        verify (exactly = 1) { mockedMessageCreator.create() }
    }

    @Test
    fun `deve falhar em inicializar o Twilio`(){
        val sms = Sms("+1477777777", "Lorem ipsum")
        every { Twilio.init(any<String>(), any<String>()) } throws RuntimeException("Erro inesperado")

       assertThrows<RuntimeException> { sender.send(sms) }
    }

    @Test
    fun `deve falhar em criar o template de mensagem`(){
        val sms = Sms("+1477777777", "Lorem ipsum")
        every { Twilio.init(any<String>(), any<String>()) } just Runs
        every { Message.creator(PhoneNumber(sms.destiny), any<PhoneNumber>(), sms.body) } throws
                RuntimeException("Erro inesperado")

        assertThrows<RuntimeException> { sender.send(sms) }
    }


    @Test
    fun `deve falhar em enviar a mensagem`(){
        val sms = Sms("+1477777777", "Lorem ipsum")
        every { Twilio.init(any<String>(), any<String>()) } just Runs
        every { Message.creator(PhoneNumber(sms.destiny), any<PhoneNumber>(), sms.body) } returns mockedMessageCreator
        every { mockedMessageCreator.create() } throws RuntimeException("Erro inesperado")

        assertThrows<RuntimeException> { sender.send(sms) }
    }
}