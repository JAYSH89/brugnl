package nl.jaysh.brugnl.features.helper

import nl.jaysh.brugnl.features.authentication.model.AuthenticationRequest

object AuthenticationHelper {
    val VALID_AUTHENTICATION_REQUEST = AuthenticationRequest(
        email = "test@example.com",
        password = "testPass123$",
    )
}