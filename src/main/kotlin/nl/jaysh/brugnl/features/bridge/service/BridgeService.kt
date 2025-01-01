package nl.jaysh.brugnl.features.bridge.service

import nl.jaysh.brugnl.core.data.repository.BridgeRepository
import org.springframework.stereotype.Service

@Service
class BridgeService(private val repository: BridgeRepository) {

    suspend fun getAllBridges() {
        repository.getAllBridges()
    }

    suspend fun refreshBridges() {
        repository.refreshBridges()
    }
}