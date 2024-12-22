package nl.jaysh.brugnl.core.data.authentication

import org.springframework.stereotype.Repository

@Repository
class InMemoryAuthenticationRepository : AuthenticationRepository {
    override fun register(email: String, password: String) {}
    override fun login(email: String, password: String) {}
    override fun refresh(token: String) {}
    override fun logout() {}
}