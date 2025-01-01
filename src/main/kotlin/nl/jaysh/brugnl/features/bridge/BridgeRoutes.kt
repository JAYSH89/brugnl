package nl.jaysh.brugnl.features.bridge

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class BridgeRoutes(private val bridgeHandler: BridgeHandler) {
    @Bean
    fun bridgeRouter(): RouterFunction<ServerResponse> = coRouter {
        "/api/v1/bridge".nest {
            GET("", bridgeHandler::getAllBridges)
            GET("/refresh", bridgeHandler::refreshBridges)
        }
    }
}