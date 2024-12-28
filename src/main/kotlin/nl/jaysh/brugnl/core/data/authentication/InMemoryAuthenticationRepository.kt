package nl.jaysh.brugnl.core.data.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Repository

@Repository
class InMemoryAuthenticationRepository : AuthenticationRepository {

    private val authenticationResponse = AuthenticationResponse(
        kind = "identitytoolkit#VerifyPasswordResponse",
        localId = "localId",
        email = "test@example.com",
        displayName = "",
        idToken = "idToken",
        registered = true,
        refreshToken = "refreshToken",
        expiresIn = "3600",
    )

    private val refreshResponse = RefreshResponse(
        accessToken = "accessToken",
        expiresIn = "3600",
        tokenType = "Bearer",
        refreshToken = "refreshToken",
        idToken = "idToken",
        userId = "userId",
        projectId = "projectId",
    )

    override suspend fun register(email: String, password: String): AuthenticationResponse {
        return authenticationResponse.copy(email = email)
    }

    override suspend fun login(email: String, password: String): AuthenticationResponse {
        return authenticationResponse.copy(email = email)
    }

    override suspend fun refresh(refreshToken: String): RefreshResponse {
        return refreshResponse
    }

    override suspend fun logout() {
        TODO()
    }
}