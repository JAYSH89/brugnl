package nl.jaysh.brugnl.core.data.repository

import nl.jaysh.brugnl.core.data.remote.ndw.NDWApi
import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.springframework.stereotype.Repository

@Repository
class BridgeRepository(private val ndw: NDWApi) {

    // Return bridges from DB (no refresh)
    suspend fun getAllBridges(): List<Bridge> {
        return refreshBridges()
    }

    // Add param to indicate refresh from cron or manual
    suspend fun refreshBridges(): List<Bridge> {
        // Fetch remote bridges

        // Save remote bridges in DB with datetime + indication who started fetch

        // Return the saved bridges from DB

        return ndw.fetchBridges()
    }
}