package nl.jaysh.brugnl.core.data.authentication

import com.google.firebase.auth.FirebaseAuthException
import nl.jaysh.brugnl.core.data.dto.FirebaseAuthenticationRequestDTO
import nl.jaysh.brugnl.core.data.dto.FirebaseRefreshRequestDTO
import nl.jaysh.brugnl.core.data.dto.toAuthenticationResponse
import nl.jaysh.brugnl.core.data.dto.toRefreshResponse
import nl.jaysh.brugnl.core.data.remote.FirebaseApiImpl
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import java.net.URISyntaxException

@Repository
@Primary
class AuthenticationRepositoryImpl(private val firebaseApi: FirebaseApiImpl) : AuthenticationRepository {

    @Throws(FirebaseAuthException::class)
    override suspend fun register(email: String, password: String): AuthenticationResponse {
        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val response = firebaseApi.register(request = request)

        return response.toAuthenticationResponse()
    }

    @Throws(URISyntaxException::class)
    override suspend fun login(email: String, password: String): AuthenticationResponse {
        val request = FirebaseAuthenticationRequestDTO(email = email, password = password)
        val response = firebaseApi.login(request = request)

        return response.toAuthenticationResponse()
    }

    override suspend fun refresh(refreshToken: String): RefreshResponse {
        val request = FirebaseRefreshRequestDTO(refreshToken = refreshToken)
        val response = firebaseApi.refreshToken(request = request)

        return response.toRefreshResponse()
    }

    override suspend fun logout() {
        firebaseApi.logout()
    }
}