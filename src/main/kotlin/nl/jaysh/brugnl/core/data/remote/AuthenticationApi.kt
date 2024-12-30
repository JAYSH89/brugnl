package nl.jaysh.brugnl.core.data.remote

import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse

interface AuthenticationApi {
    suspend fun register(email: String, password: String): AuthenticationResponse
    suspend fun login(email: String, password: String): AuthenticationResponse
    suspend fun refreshToken(refreshToken: String): RefreshResponse
    fun verify(idToken: String): AuthenticationToken
    suspend fun logout()
}