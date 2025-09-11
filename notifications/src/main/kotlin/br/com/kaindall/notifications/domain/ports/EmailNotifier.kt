package br.com.kaindall.notifications.domain.ports

import br.com.kaindall.notifications.domain.entities.Notification

interface EmailNotifier {
    fun send(notification: Notification)
}