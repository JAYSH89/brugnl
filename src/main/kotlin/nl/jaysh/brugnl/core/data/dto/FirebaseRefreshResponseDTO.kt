package nl.jaysh.brugnl.core.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse

data class FirebaseRefreshResponseDTO(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("id_token") val idToken: String,
    @JsonProperty("user_id") val userId: String,
    @JsonProperty("project_id") val projectId: String,
)

fun FirebaseRefreshResponseDTO.toRefreshResponse(): RefreshResponse = RefreshResponse(
    accessToken = this.accessToken,
    expiresIn = this.expiresIn,
    tokenType = this.tokenType,
    refreshToken = this.refreshToken,
    idToken = this.idToken,
    userId = this.userId,
    projectId = this.projectId,
)
