package nl.jaysh.brugnl.core.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse

data class FirebaseAuthenticationResponseDTO(
    @JsonProperty("kind") val kind: String,
    @JsonProperty("localId") val localId: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("displayName") val displayName: String?,
    @JsonProperty("idToken") val idToken: String,
    @JsonProperty("registered") val registered: Boolean?,
    @JsonProperty("refreshToken") val refreshToken: String,
    @JsonProperty("expiresIn") val expiresIn: String,
)

fun FirebaseAuthenticationResponseDTO.toAuthenticationResponse(): AuthenticationResponse = AuthenticationResponse(
    kind = this.kind,
    localId = this.localId,
    email = this.email,
    displayName = this.displayName,
    idToken = this.idToken,
    registered = this.registered,
    refreshToken = this.refreshToken,
    expiresIn = this.expiresIn,
)