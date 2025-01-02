package nl.jaysh.brugnl.features.bridge

import nl.jaysh.brugnl.core.data.repository.BridgeRepository
import nl.jaysh.brugnl.core.model.FetchType
import nl.jaysh.brugnl.features.bridge.model.BridgeResponse
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BridgeService(private val repository: BridgeRepository) {

    fun getAllBridges(): List<BridgeResponse> {
        val bridges = repository.getAllBridges()
        return bridges.map { BridgeResponse.fromBridge(it) }
    }

    fun getBridgesByCity(city: String): List<BridgeResponse> {
        val bridges = repository.getBridgesByCity(city)
        return bridges.map { BridgeResponse.fromBridge(it) }
    }

    fun refreshBridges(fetchType: FetchType): List<BridgeResponse> {
        val bridges = repository.refreshBridges(fetchType = fetchType)
        return bridges.map { BridgeResponse.fromBridge(it) }
    }

    fun deleteBridges(date: LocalDate = LocalDate.now()): Boolean {
        return repository.deleteBridges(date = date)
    }
}