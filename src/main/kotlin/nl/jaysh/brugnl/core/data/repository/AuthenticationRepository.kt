package nl.jaysh.brugnl.core.data.repository

import com.google.firebase.auth.FirebaseAuthException
import nl.jaysh.brugnl.core.data.remote.authentication.AuthenticationApi
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Repository
import java.net.URISyntaxException

@Repository
class AuthenticationRepository(private val authenticationApi: AuthenticationApi) {

    @Throws(URISyntaxException::class)
    suspend fun register(email: String, password: String): AuthenticationResponse = authenticationApi.register(
        email = email,
        password = password,
    )

    @Throws(URISyntaxException::class)
    suspend fun login(email: String, password: String): AuthenticationResponse = authenticationApi.login(
        email = email,
        password = password,
    )

    @Throws(URISyntaxException::class)
    suspend fun refresh(refreshToken: String): RefreshResponse = authenticationApi.refreshToken(
        refreshToken = refreshToken,
    )

    @Throws(FirebaseAuthException::class)
    fun verify(idToken: String): AuthenticationToken = authenticationApi.verify(
        idToken = idToken,
    )

    suspend fun logout() {
        authenticationApi.logout()
    }
}