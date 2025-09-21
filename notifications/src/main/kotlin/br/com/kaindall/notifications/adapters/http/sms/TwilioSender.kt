package br.com.kaindall.notifications.adapters.http.sms

import br.com.kaindall.notifications.domain.entities.Sms
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TwilioSender(
    @Value("\${twilio.account.sid}") private val accountSid: String,
    @Value("\${twilio.account.token}") private val accountToken: String,
    @Value("\${twilio.account.number}") private val originNumber: String
) : SmsNotifier {
    private val logger = LoggerFactory.getLogger(TwilioSender::class.java)

    override fun send(sms: Sms) {
        Twilio.init(accountSid, accountToken)
        val message = Message
            .creator(PhoneNumber(sms.destiny), PhoneNumber(originNumber), sms.body)
            .create()
        logger.info("Mensagem enviada para {}", message.to)
    }
}