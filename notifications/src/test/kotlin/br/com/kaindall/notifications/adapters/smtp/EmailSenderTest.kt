package br.com.kaindall.notifications.adapters.smtp

import br.com.kaindall.notifications.adapters.smtp.EmailSender
import br.com.kaindall.notifications.domain.entities.Email
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

@ExtendWith(MockKExtension::class)
class EmailSenderTest {

    @MockK
    lateinit var mailSender: JavaMailSender

    @InjectMockKs
    lateinit var emailSender: EmailSender

    @Test
    fun `deve ter sucesso em enviar o e-mail para destinatário único`(){
        val email = Email(arrayOf("teste1@email.com"), "Assunto Lorem Ipsum", "Body Lorem Ipsum")
        every { mailSender.send(any<SimpleMailMessage>()) } just Runs

        emailSender.send(email)

        verify (exactly = 1) { mailSender.send(any<SimpleMailMessage>()) }
    }

    @Test
    fun `deve ter sucesso em enviar o e-mail para destinatários múltiplos`(){
        val email = Email(
            arrayOf("teste1@email.com", "teste2@email.com", "teste3@email.com"),
            "Assunto Lorem Ipsum",
            "Body Lorem Ipsum"
        )
        every { mailSender.send(any<SimpleMailMessage>()) } just Runs

        emailSender.send(email)

        verify (exactly = 1) { mailSender.send(any<SimpleMailMessage>()) }
    }

    @Test
    fun `deve falhar em enviar o e-mail por erro no serviço`(){
        val email = Email(arrayOf("teste1@email.com"), "Assunto Lorem Ipsum", "Body Lorem Ipsum")
        every { mailSender.send(any<SimpleMailMessage>()) } throws RuntimeException("Serviço indisponível")

        assertThrows<RuntimeException> { emailSender.send(email) }
    }
}