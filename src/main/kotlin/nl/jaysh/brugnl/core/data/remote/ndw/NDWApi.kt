package nl.jaysh.brugnl.core.data.remote.ndw

import nl.jaysh.brugnl.core.model.bridge.Bridge

interface NDWApi {
    suspend fun fetchBridges(): List<Bridge>
}