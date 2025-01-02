package nl.jaysh.brugnl.core.data.local.entity

import nl.jaysh.brugnl.core.data.local.table.BridgeTable
import nl.jaysh.brugnl.core.model.FetchType
import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.UUID

@Component
@Transactional
class BridgeDao {

    fun findAll(): List<BridgeEntity> = BridgeEntity.all().toList()

    fun findById(id: UUID): BridgeEntity? = BridgeEntity.findById(id)

    fun findByCity(city: String): List<BridgeEntity> = BridgeEntity.find { BridgeTable.city eq city }.toList()

    fun findByDate(date: LocalDate): List<BridgeEntity> {
        val start = date.atStartOfDay()
        val end = date.plusDays(1).atStartOfDay()

        val result = BridgeEntity.find {
            (BridgeTable.createdAt greaterEq start) and (BridgeTable.createdAt less end)
        }

        return result.toList()
    }

    fun saveAll(bridges: List<Bridge>, type: FetchType): List<BridgeEntity> {
        val savedBridges = mutableListOf<BridgeEntity>()

        bridges.forEach { bridge ->
            val result = save(bridge = bridge, type = type)
            savedBridges.add(result)
        }

        return savedBridges.toList()
    }

    fun save(bridge: Bridge, type: FetchType): BridgeEntity = BridgeEntity.new {
        locationId = bridge.locationId
        isrs = bridge.isrs
        viId = bridge.viId
        vinCode = bridge.vinCode
        name = bridge.name
        city = bridge.city
        latitude = bridge.latitude
        longitude = bridge.longitude
        administrator = bridge.administrator
        administratorPhoneNumber = bridge.administratorPhoneNumber
        method = bridge.method
        supplier = bridge.supplier
        isRequired = bridge.isRequired
        contract = bridge.contract
        isRvm = bridge.isRvm
        isSmr = bridge.isSmr
        isActive = bridge.isActive
        validFrom = bridge.validFrom
        validUntil = bridge.validUntil
        fetchType = type
    }


    fun delete(bridge: Bridge): Int {
        requireNotNull(bridge.id)
        val entity = findById(id = bridge.id)

        requireNotNull(entity) { "${bridge.id} not found" }
        entity.delete()

        return 1
    }

    fun deleteByDate(date: LocalDate): Int {
        val entities = findByDate(date = date)
        entities.forEach { entity -> entity.delete() }

        return entities.size
    }
}