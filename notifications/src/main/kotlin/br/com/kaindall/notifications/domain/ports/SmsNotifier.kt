package br.com.kaindall.notifications.domain.ports

import br.com.kaindall.notifications.domain.entities.Sms

interface SmsNotifier {
    fun send(sms: Sms)
}