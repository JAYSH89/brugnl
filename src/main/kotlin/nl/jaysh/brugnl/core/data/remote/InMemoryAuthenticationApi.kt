package nl.jaysh.brugnl.core.data.remote

import nl.jaysh.brugnl.core.model.user.InMemoryUser
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Component

@Component
class InMemoryAuthenticationApi : AuthenticationApi {

    private val inMemoryUsers = mutableListOf<InMemoryUser>()

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

    @Throws(IllegalArgumentException::class)
    override suspend fun register(email: String, password: String): AuthenticationResponse {
        val savedUser = inMemoryUsers.find { it.email == email }

        if (savedUser == null) {
            val newUser = InMemoryUser(email = email, password = password)
            inMemoryUsers.add(newUser)
        } else {
            throw IllegalArgumentException("user already exists")
        }

        return authenticationResponse.copy(email = email)
    }

    @Throws(IllegalArgumentException::class)
    override suspend fun login(email: String, password: String): AuthenticationResponse {
        val savedUser = inMemoryUsers.find { it.email == email }
        if (savedUser == null) throw IllegalArgumentException("user does not exists")

        if (savedUser.password == password) {
            return authenticationResponse.copy(email = email)
        } else {
            throw IllegalArgumentException("password does not match")
        }
    }

    override suspend fun refreshToken(refreshToken: String): RefreshResponse {
        return refreshResponse
    }

    override fun verify(idToken: String): AuthenticationToken {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}