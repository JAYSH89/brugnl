package nl.jaysh.brugnl.features.helper

import nl.jaysh.brugnl.features.authentication.model.AuthenticationRequest
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshRequest
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse

object AuthenticationHelper {
    val VALID_AUTHENTICATION_REQUEST = AuthenticationRequest(email = "test@example.com", password = "testPass123$")

    val VALID_REFRESH_REQUEST = RefreshRequest(refreshToken = "testRefreshToken")

    val AUTHENTICATION_RESPONSE = AuthenticationResponse(
        kind = "identitytoolkit#VerifyPasswordResponse",
        localId = "localId",
        email = "test@example.com",
        displayName = "",
        idToken = "idToken",
        registered = true,
        refreshToken = "refreshToken",
        expiresIn = "3600",
    )

    val REFRESH_RESPONSE = RefreshResponse(
        accessToken = "accessToken",
        expiresIn = "3600",
        tokenType = "Bearer",
        refreshToken = "refreshToken",
        idToken = "idToken",
        userId = "userId",
        projectId = "projectId",
    )
}