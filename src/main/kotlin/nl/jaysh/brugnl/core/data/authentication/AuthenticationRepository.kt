package nl.jaysh.brugnl.core.data.authentication

import com.google.firebase.auth.UserRecord

interface AuthenticationRepository {
    fun register(email: String, password: String)
    fun login(email: String, password: String)
    fun refresh(token: String)
    fun logout()
}