package nl.jaysh.brugnl.core.data.remote.ndw

import nl.jaysh.brugnl.core.model.bridge.Bridge

interface NDWApi {
    fun fetchBridges(): List<Bridge>
}