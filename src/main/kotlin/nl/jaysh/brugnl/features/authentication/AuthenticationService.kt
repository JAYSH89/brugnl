package nl.jaysh.brugnl.features.authentication

import com.google.firebase.auth.FirebaseAuthException
import nl.jaysh.brugnl.core.data.repository.AuthenticationRepository
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.AuthenticationToken
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.stereotype.Service
import java.net.URISyntaxException

@Service
class AuthenticationService(private val repository: AuthenticationRepository) {

    @Throws(URISyntaxException::class)
    fun register(email: String, password: String): AuthenticationResponse {
        return repository.register(email, password)
    }

    @Throws(URISyntaxException::class)
    fun login(email: String, password: String): AuthenticationResponse {
        return repository.login(email, password)
    }

    @Throws(URISyntaxException::class)
    fun refresh(token: String): RefreshResponse {
        return repository.refresh(token)
    }

    @Throws(FirebaseAuthException::class)
    fun verify(idToken: String): AuthenticationToken {
        return repository.verify(idToken)
    }

    fun logout() {
        repository.logout()
    }
}