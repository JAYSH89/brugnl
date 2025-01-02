package nl.jaysh.brugnl.core.data.local.table

import nl.jaysh.brugnl.core.model.FetchType
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object BridgeTable : UUIDTable() {
    val locationId = varchar(name = "location_id", length = 255)
    val isrs = varchar(name = "isrs", length = 255)
    val viId = integer(name = "vi_id")
    val vinCode = integer(name = "vin_code")
    val name = varchar(name = "name", length = 255)
    val city = varchar(name = "city", length = 255)
    val latitude = double(name = "latitude")
    val longitude = double(name = "longitude")
    val administrator = varchar(name = "administrator", length = 64)
    val administratorPhoneNumber = varchar(name = "administrator_phone_number", length = 64)
    val method = varchar(name = "method", length = 255)
    val supplier = varchar(name = "supplier", length = 255)
    val isRequired = bool(name = "is_required")
    val contract = varchar(name = "contract", length = 255)
    val isRvm = bool(name = "is_rm")
    val isSmr = bool(name = "is_smr")
    val isActive = bool(name = "is_active")
    val validFrom = varchar(name = "valid_from", length = 64)
    val validUntil = varchar(name = "valid_until", length = 64).nullable()
    val fetchType = enumeration<FetchType>(name = "fetch_type")
    val createdAt = datetime(name = "created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime(name = "updated_at")
}