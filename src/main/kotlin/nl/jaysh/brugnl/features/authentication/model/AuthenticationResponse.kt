package nl.jaysh.brugnl.features.authentication.model

data class AuthenticationResponse(
    val kind: String,
    val localId: String,
    val email: String,
    val displayName: String?,
    val idToken: String,
    val registered: Boolean?,
    val refreshToken: String,
    val expiresIn: String,
)
