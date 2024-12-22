package nl.jaysh.brugnl.features.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationRequest
import nl.jaysh.brugnl.features.authentication.service.AuthenticationService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class AuthenticationHandler(private val service: AuthenticationService) {
    suspend fun register(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.awaitBody<AuthenticationRequest>()

        val register = service.register(
            email = request.email,
            password = request.password,
        )

        val result = mapOf("response" to register)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }

    suspend fun login(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.awaitBody<AuthenticationRequest>()

        val login = service.login(
            email = request.email,
            password = request.password,
        )

        val result = mapOf("response" to login)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }

    suspend fun refresh(serverRequest: ServerRequest): ServerResponse {
        val refresh = service.refresh(token = "")

        val result = mapOf("response" to refresh)
        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}