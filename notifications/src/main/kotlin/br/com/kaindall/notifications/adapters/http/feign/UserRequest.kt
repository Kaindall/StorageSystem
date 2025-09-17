package br.com.kaindall.notifications.adapters.http.feign

import br.com.kaindall.notifications.domain.entities.User
import br.com.kaindall.notifications.domain.ports.UserFinder
import org.springframework.stereotype.Service

@Service
class UserRequest : UserFinder {
    override fun find(userId: Long): User {
        return User("Teste", "gustavozanete2@gmail.com")
    }
}