package br.com.kaindall.notifications.adapters.smtp

import br.com.kaindall.notifications.domain.entities.Email
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class EmailSenderTest {

    private val javaMailSender = mockk<JavaMailSender>()
    private val emailSender = EmailSender("teste@email.com", javaMailSender)

    @Test
    fun `deve ter sucesso em enviar o e-mail para destinatário único`(){
        val email = Email(arrayOf("teste1@email.com"), "Assunto Lorem Ipsum", "Body Lorem Ipsum")
        every { javaMailSender.send(any<SimpleMailMessage>()) } just Runs

        emailSender.send(email)

        verify (exactly = 1) { javaMailSender.send(any<SimpleMailMessage>()) }
    }

    @Test
    fun `deve ter sucesso em enviar o e-mail para destinatários múltiplos`(){
        val email = Email(
            arrayOf("teste1@email.com", "teste2@email.com", "teste3@email.com"),
            "Assunto Lorem Ipsum",
            "Body Lorem Ipsum"
        )
        every { javaMailSender.send(any<SimpleMailMessage>()) } just Runs

        emailSender.send(email)

        verify (exactly = 1) { javaMailSender.send(any<SimpleMailMessage>()) }
    }

    @Test
    fun `deve falhar em enviar o e-mail por erro no serviço`(){
        val email = Email(arrayOf("teste1@email.com"), "Assunto Lorem Ipsum", "Body Lorem Ipsum")
        every { javaMailSender.send(any<SimpleMailMessage>()) } throws RuntimeException("Serviço indisponível")

        assertThrows<RuntimeException> { emailSender.send(email) }
    }
}