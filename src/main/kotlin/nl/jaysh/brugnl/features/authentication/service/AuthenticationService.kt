package nl.jaysh.brugnl.features.authentication.service

import nl.jaysh.brugnl.core.data.authentication.AuthenticationRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val authenticationRepository: AuthenticationRepository) {
    suspend fun register(email: String, password: String): String {
        authenticationRepository.register(
            email = email,
            password = password,
        )

        return "register"
    }

    suspend fun login(email: String, password: String): String {
        authenticationRepository.login(
            email = email,
            password = password,
        )

        return "login"
    }

    suspend fun refresh(token: String): String {
        authenticationRepository.refresh(token)
        return "refresh"
    }
}