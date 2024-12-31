package nl.jaysh.brugnl.features.authentication.model

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
