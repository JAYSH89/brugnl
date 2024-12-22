package nl.jaysh.brugnl

import nl.jaysh.brugnl.features.authentication.AuthenticationRoutes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions

@Configuration
class RouterConfig(private val authenticationRoutes: AuthenticationRoutes) {
    @Bean
    fun router(): RouterFunction<*> = RouterFunctions
        .route()
        .add(authenticationRoutes.authenticationRouter())
        .build()
}