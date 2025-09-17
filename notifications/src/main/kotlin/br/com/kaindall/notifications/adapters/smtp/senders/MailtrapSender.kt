package br.com.kaindall.notifications.adapters.smtp.senders

import br.com.kaindall.notifications.domain.entities.Email
import br.com.kaindall.notifications.domain.ports.EmailNotifier
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailtrapSender(private val mailSender: JavaMailSender) : EmailNotifier {
    private val EMAIL_ORIGIN = "teste@email.com"

    override fun send(email: Email) {
        var mailTemplate = SimpleMailMessage()
        mailTemplate.from = EMAIL_ORIGIN
        mailTemplate.to(email.destiniesToSend)
        mailTemplate.subject = email.subject
        mailTemplate.text = email.body
    }

}