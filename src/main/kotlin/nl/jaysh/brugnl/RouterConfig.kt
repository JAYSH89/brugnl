package nl.jaysh.brugnl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions

@Configuration
class RouterConfig {
    @Bean
    fun router(): RouterFunction<*> = RouterFunctions
        .route()
        .build()
}