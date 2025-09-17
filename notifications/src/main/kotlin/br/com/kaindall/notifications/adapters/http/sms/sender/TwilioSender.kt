package br.com.kaindall.notifications.adapters.http.sms.sender

import br.com.kaindall.notifications.domain.entities.Notification
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import org.springframework.stereotype.Service

@Service
class TwilioSender: SmsNotifier {
    override fun send(notification: Notification) {
        TODO("Not yet implemented")
    }
}