package nl.jaysh.brugnl.core.data.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty

data class FirebaseAuthenticationRequestDTO(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("returnSecureToken") val returnSecureToken: Boolean = true,
)