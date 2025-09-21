package br.com.kaindall.notifications.adapters.smtp.senders

import br.com.kaindall.notifications.domain.entities.Email
import br.com.kaindall.notifications.domain.ports.EmailNotifier
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailtrapSender(private val mailSender: JavaMailSender) : EmailNotifier {
    private val EMAIL_ORIGIN = "teste@email.com"
    private val logger = LoggerFactory.getLogger(MailtrapSender::class.java)

    override fun send(email: Email) {
        var mailTemplate = SimpleMailMessage()
        mailTemplate.from = EMAIL_ORIGIN
        mailTemplate.setTo(*email.destiniesToSend)
        mailTemplate.subject = email.subject
        mailTemplate.text = email.body
        mailSender.send(mailTemplate)
        logger.info("Email enviado com sucesso para {}", email.destiniesToSend)
    }
}