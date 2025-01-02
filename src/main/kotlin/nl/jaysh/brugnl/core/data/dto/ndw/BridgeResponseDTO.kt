package nl.jaysh.brugnl.core.data.dto.ndw

import com.fasterxml.jackson.annotation.JsonProperty
import nl.jaysh.brugnl.core.model.bridge.Bridge

data class BridgeResponseDTO(
    @JsonProperty("location_id") val locationId: String,
    @JsonProperty("isrs") val isrs: String,
    @JsonProperty("vi_id") val viId: Int,
    @JsonProperty("vin_code") val vinCode: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("latitude") val latitude: Double,
    @JsonProperty("longitude") val longitude: Double,
    @JsonProperty("administrator") val administrator: String,
    @JsonProperty("administrator_phone_number") val administratorPhoneNumber: String,
    @JsonProperty("method") val method: String,
    @JsonProperty("supplier") val supplier: String,
    @JsonProperty("is_required") val isRequired: Boolean,
    @JsonProperty("contract") val contract: String,
    @JsonProperty("is_rvm") val isRvm: Boolean,
    @JsonProperty("is_smr") val isSmr: Boolean,
    @JsonProperty("is_active") val isActive: Boolean,
    @JsonProperty("valid_from") val validFrom: String,
    @JsonProperty("valid_until") val validUntil: String?,
)

fun BridgeResponseDTO.toBridge(): Bridge = Bridge(
    id = null,
    locationId = this.locationId,
    isrs = this.isrs,
    viId = this.viId,
    vinCode = this.vinCode,
    name = this.name,
    city = this.city,
    latitude = this.latitude,
    longitude = this.longitude,
    administrator = this.administrator,
    administratorPhoneNumber = this.administratorPhoneNumber,
    method = this.method,
    supplier = this.supplier,
    isRequired = this.isRequired,
    contract = this.contract,
    isRvm = this.isRvm,
    isSmr = this.isSmr,
    isActive = this.isActive,
    validFrom = this.validFrom,
    validUntil = this.validUntil,
    createdAt = null,
    updatedAt = null,
)
