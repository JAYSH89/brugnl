package nl.jaysh.brugnl.features.authentication.model

data class RefreshResponse(
    val accessToken: String,
    val expiresIn: String,
    val tokenType: String,
    val refreshToken: String,
    val idToken: String,
    val userId: String,
    val projectId: String,
)
