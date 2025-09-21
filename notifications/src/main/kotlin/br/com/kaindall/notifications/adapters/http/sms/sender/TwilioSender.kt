package br.com.kaindall.notifications.adapters.http.sms.sender

import br.com.kaindall.notifications.domain.entities.Sms
import br.com.kaindall.notifications.domain.ports.SmsNotifier
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TwilioSender() : SmsNotifier {
    @Value("\${twilio.account.sid}")
    private val ACCOUNT_SID: String? = null

    @Value("\${twilio.account.token}")
    private val ACCOUNT_TOKEN: String? = null

    @Value("\${twilio.number}")
    private val ORIGIN_NUMBER: String? = null

    private val logger = LoggerFactory.getLogger(TwilioSender::class.java)

    override fun send(sms: Sms) {
        Twilio.init(ACCOUNT_SID, ACCOUNT_TOKEN)
        val message = Message
            .creator(PhoneNumber(sms.destiny), PhoneNumber(ORIGIN_NUMBER), sms.body)
            .create()
        logger.info("Mensagem enviada para {}", message.to)
    }
}