package nl.jaysh.brugnl.features.authentication

import nl.jaysh.brugnl.features.authentication.model.AuthenticationRequest
import nl.jaysh.brugnl.features.authentication.model.AuthenticationResponse
import nl.jaysh.brugnl.features.authentication.model.RefreshRequest
import nl.jaysh.brugnl.features.authentication.model.RefreshResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URISyntaxException

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(private val service: AuthenticationService) {

    @Throws(URISyntaxException::class)
    @PostMapping("/register")
    fun register(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return service.register(request.email, request.password)
    }

    @Throws(URISyntaxException::class)
    @PostMapping("/login")
    fun login(@RequestBody request: AuthenticationRequest): AuthenticationResponse {
        return service.login(request.email, request.password)
    }

    @Throws(URISyntaxException::class)
    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): RefreshResponse {
        return service.refresh(token = request.refreshToken)
    }
}