package nl.jaysh.brugnl.core.data.local.entity

import nl.jaysh.brugnl.core.data.local.table.BridgeTable
import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class BridgeEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var locationId by BridgeTable.locationId
    var isrs by BridgeTable.isrs
    var viId by BridgeTable.viId
    var vinCode by BridgeTable.vinCode
    var name by BridgeTable.name
    var city by BridgeTable.city
    var latitude by BridgeTable.latitude
    var longitude by BridgeTable.longitude
    var administrator by BridgeTable.administrator
    var administratorPhoneNumber by BridgeTable.administratorPhoneNumber
    var method by BridgeTable.method
    var supplier by BridgeTable.supplier
    var isRequired by BridgeTable.isRequired
    var contract by BridgeTable.contract
    var isRvm by BridgeTable.isRvm
    var isSmr by BridgeTable.isSmr
    var isActive by BridgeTable.isActive
    var validFrom by BridgeTable.validFrom
    var validUntil by BridgeTable.validUntil
    var fetchType by BridgeTable.fetchType
    var createdAt by BridgeTable.createdAt
    var updatedAt by BridgeTable.updatedAt

    companion object : UUIDEntityClass<BridgeEntity>(BridgeTable)
}

fun BridgeEntity.toBridge() = Bridge(
    id = this.id.value,
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
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
)