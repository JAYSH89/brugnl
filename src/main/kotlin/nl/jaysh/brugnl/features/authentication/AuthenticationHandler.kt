package nl.jaysh.brugnl.features.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationRequest
import nl.jaysh.brugnl.features.authentication.model.RefreshRequest
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
        val result = service.register(email = request.email, password = request.password)

        return ServerResponse.ok().bodyValueAndAwait(result)
    }

    suspend fun login(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.awaitBody<AuthenticationRequest>()
        val result = service.login(email = request.email, password = request.password)

        return ServerResponse.ok().bodyValueAndAwait(result)
    }

    suspend fun refresh(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.awaitBody<RefreshRequest>()
        val result = service.refresh(token = request.refreshToken)

        return ServerResponse.ok().bodyValueAndAwait(result)
    }
}