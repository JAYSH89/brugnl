package nl.jaysh.brugnl.core.data.remote.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse

interface AuthenticationApi {
    fun register(email: String, password: String): AuthenticationResponse
    fun login(email: String, password: String): AuthenticationResponse
    fun refreshToken(refreshToken: String): RefreshResponse
    fun verify(idToken: String): AuthenticationToken
    fun logout()
}