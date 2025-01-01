package nl.jaysh.brugnl.features.bridge

import nl.jaysh.brugnl.features.bridge.service.BridgeService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class BridgeHandler(private val service: BridgeService) {

    suspend fun getAllBridges(serverRequest: ServerRequest): ServerResponse {
        service.getAllBridges()
        return ServerResponse.ok().bodyValueAndAwait("get all bridges")
    }

    suspend fun refreshBridges(serverRequest: ServerRequest): ServerResponse {
        service.refreshBridges()
        return ServerResponse.ok().bodyValueAndAwait("refresh")
    }
}