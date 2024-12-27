package nl.jaysh.brugnl.features.authentication.model

data class AuthenticationToken(
    val claims: Map<String, Any>,
    val uid: String,
    val emailVerified: Boolean,
    val email: String?,
    val name: String?,
    val issuer: String?,
    val tenantId: String?,
    val picture: String?,
)
