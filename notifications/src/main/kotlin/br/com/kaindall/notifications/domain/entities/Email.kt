package br.com.kaindall.notifications.domain.entities

data class Email(
    val destiniesToSend: Array<String>,
    val subject: String,
    val body:  String,
)
