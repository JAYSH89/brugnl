package nl.jaysh.brugnl.core.data.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Repository
@Primary
class AuthenticationRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthenticationRepository {

    @Throws(FirebaseAuthException::class)
    override fun register(email: String, password: String) {
        val request = UserRecord.CreateRequest()
            .setEmail(email)
            .setPassword(password)

        firebaseAuth.createUser(request)
    }

    override fun login(email: String, password: String) {}

    override fun refresh(token: String) {}

    override fun logout() {}
}