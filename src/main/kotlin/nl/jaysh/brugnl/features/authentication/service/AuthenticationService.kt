package nl.jaysh.brugnl.features.authentication.service

import nl.jaysh.brugnl.core.data.authentication.AuthenticationRepository
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val authenticationRepository: AuthenticationRepository) {

    suspend fun register(email: String, password: String): AuthenticationResponse = authenticationRepository
        .register(email = email, password = password)

    suspend fun login(email: String, password: String): AuthenticationResponse = authenticationRepository
        .login(email = email, password = password)

    suspend fun refresh(token: String): RefreshResponse = authenticationRepository
        .refresh(token)
}