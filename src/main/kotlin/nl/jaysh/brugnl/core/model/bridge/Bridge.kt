package nl.jaysh.brugnl.core.model.bridge

import java.time.LocalDateTime
import java.util.*

data class Bridge(
    val id: UUID?,
    val locationId: String,
    val isrs: String,
    val viId: Int,
    val vinCode: Int,
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val administrator: String,
    val administratorPhoneNumber: String,
    val method: String,
    val supplier: String,
    val isRequired: Boolean,
    val contract: String,
    val isRvm: Boolean,
    val isSmr: Boolean,
    val isActive: Boolean,
    val validFrom: String,
    val validUntil: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
)
