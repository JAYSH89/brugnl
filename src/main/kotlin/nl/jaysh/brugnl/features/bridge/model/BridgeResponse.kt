package nl.jaysh.brugnl.features.bridge.model

import nl.jaysh.brugnl.core.model.bridge.Bridge

data class BridgeResponse(
    val locationId: String,
    val isrs: String,
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val isActive: Boolean,
) {
    companion object {
        fun fromBridge(bridge: Bridge) = BridgeResponse(
            locationId = bridge.locationId,
            isrs = bridge.isrs,
            name = bridge.name,
            city = bridge.city,
            latitude = bridge.latitude,
            longitude = bridge.longitude,
            isActive = bridge.isActive,
        )
    }
}
