package nl.jaysh.brugnl.core.data.dto.firebase

import com.fasterxml.jackson.annotation.JsonProperty

data class FirebaseRefreshRequestDTO(
    @JsonProperty("grant_type") val grantType: String = "refresh_token",
    @JsonProperty("refresh_token") val refreshToken: String,
)