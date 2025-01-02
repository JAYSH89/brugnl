package nl.jaysh.brugnl.core.data.remote.ndw

import nl.jaysh.brugnl.core.config.ApiConfig
import nl.jaysh.brugnl.core.data.dto.ndw.BridgeResponseDTO
import nl.jaysh.brugnl.core.data.dto.ndw.toBridge
import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder

@Component
@Primary
class NDWApiImpl(private val restTemplate: RestTemplate, private val apiConfig: ApiConfig) : NDWApi {

    override fun fetchBridges(): List<Bridge> {
        val path = "/static-road-data/validated-locations/v1/bridge/"
        val url = apiConfig.ndwBaseUrl + path

        val uri = UriComponentsBuilder
            .fromUriString(url)
            .build()
            .toUri()

        val response = restTemplate.getForObject<List<BridgeResponseDTO>>(uri)
        return response.map { bridgeDTO -> bridgeDTO.toBridge() }
    }
}