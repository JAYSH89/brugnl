package nl.jaysh.brugnl.features.authentication.service

import org.springframework.stereotype.Service

@Service
class AuthenticationService {
    suspend fun register(): String = "register"
    suspend fun login(): String = "login"
}