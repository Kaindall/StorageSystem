package br.com.kaindall.notifications.domain.ports

import br.com.kaindall.notifications.domain.entities.Email

interface EmailNotifier {
    fun send(email: Email)
}