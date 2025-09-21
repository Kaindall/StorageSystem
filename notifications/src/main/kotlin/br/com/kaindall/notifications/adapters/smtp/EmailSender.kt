package br.com.kaindall.notifications.adapters.smtp

import br.com.kaindall.notifications.domain.entities.Email
import br.com.kaindall.notifications.domain.ports.EmailNotifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSender(
    @Value("\${spring.mail.origin}") private val originEmail: String,
    private val mailSender: JavaMailSender
) : EmailNotifier {
    private val logger = LoggerFactory.getLogger(EmailSender::class.java)

    override fun send(email: Email) {
        val mailTemplate = SimpleMailMessage()
        mailTemplate.from = originEmail
        mailTemplate.setTo(*email.destiniesToSend)
        mailTemplate.subject = email.subject
        mailTemplate.text = email.body

        mailSender.send(mailTemplate)
        logger.info("Email enviado com sucesso para {}", email.destiniesToSend)
    }
}