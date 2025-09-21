package br.com.kaindall.notifications.adapters.http.feign

import br.com.kaindall.notifications.domain.entities.User
import br.com.kaindall.notifications.domain.ports.UserFinder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserRequest : UserFinder {
    private val logger = LoggerFactory.getLogger(UserRequest::class.java)

    override fun find(userId: Long): User {
        logger.info("Buscando usuário $userId")
        return User("Teste", "gustavozanete2@gmail.com", "+18777804236")
    }
}