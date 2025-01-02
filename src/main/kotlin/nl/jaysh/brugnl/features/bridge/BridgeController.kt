package nl.jaysh.brugnl.features.bridge

import nl.jaysh.brugnl.core.model.FetchType
import nl.jaysh.brugnl.features.bridge.model.BridgeResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/bridge")
class BridgeController(private val service: BridgeService) {

    @GetMapping
    fun getAllBridges(@RequestParam city: String? = null): List<BridgeResponse> {
        return if (city.isNullOrBlank()) {
            service.getAllBridges()
        } else {
            service.getBridgesByCity(city = city)
        }
    }

    @GetMapping("/refresh")
    fun refreshBridges(): List<BridgeResponse> {
        return service.refreshBridges(fetchType = FetchType.REST)
    }

    @DeleteMapping
    fun deleteBridges(): ResponseEntity<Void> {
        val success = service.deleteBridges()

        return if (success) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}