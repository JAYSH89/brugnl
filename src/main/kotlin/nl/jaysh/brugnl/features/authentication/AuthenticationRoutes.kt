package nl.jaysh.brugnl.features.authentication

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthenticationRoutes(private val authHandler: AuthenticationHandler) {
    @Bean
    fun authenticationRouter(): RouterFunction<ServerResponse> = coRouter {
        "/api/v1/auth".nest {
            POST("/register", authHandler::register)
            POST("/login", authHandler::login)
        }
    }
}