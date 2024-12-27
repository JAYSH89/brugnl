package nl.jaysh.brugnl.core.data.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Repository

@Repository
class InMemoryAuthenticationRepository : AuthenticationRepository {

    override suspend fun register(email: String, password: String): AuthenticationResponse {
        TODO()
    }

    override suspend fun login(email: String, password: String): AuthenticationResponse {
        TODO()
    }

    override suspend fun refresh(refreshToken: String): RefreshResponse {
        TODO()
    }

    override suspend fun logout() {
        TODO()
    }
}