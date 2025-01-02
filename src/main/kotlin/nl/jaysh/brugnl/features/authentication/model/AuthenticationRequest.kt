package nl.jaysh.brugnl.features.authentication.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationRequest(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
)
