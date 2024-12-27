package nl.jaysh.brugnl.core.data.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse

interface AuthenticationRepository {
    suspend fun register(email: String, password: String): AuthenticationResponse
    suspend fun login(email: String, password: String): AuthenticationResponse
    suspend fun refresh(refreshToken: String): RefreshResponse
    suspend fun logout()
}