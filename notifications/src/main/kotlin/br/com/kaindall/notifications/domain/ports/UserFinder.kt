package br.com.kaindall.notifications.domain.ports

import br.com.kaindall.notifications.domain.entities.User

interface UserFinder {
    fun find(userId: Long): User
}