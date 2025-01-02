package nl.jaysh.brugnl.core.data.remote.ndw

import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.springframework.stereotype.Component

@Component
class InMemoryNDWApi : NDWApi {
    override fun fetchBridges(): List<Bridge> = TODO("Not yet implemented")
}