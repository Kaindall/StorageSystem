package br.com.kaindall.notifications.domain.ports

import br.com.kaindall.notifications.domain.entities.Notification

interface SmsNotifier {
    fun send(notification: Notification)
}