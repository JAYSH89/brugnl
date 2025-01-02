package nl.jaysh.brugnl.core.data.repository

import nl.jaysh.brugnl.core.data.local.entity.BridgeDao
import nl.jaysh.brugnl.core.data.local.entity.toBridge
import nl.jaysh.brugnl.core.data.remote.ndw.NDWApi
import nl.jaysh.brugnl.core.model.FetchType
import nl.jaysh.brugnl.core.model.bridge.Bridge
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BridgeRepository(private val dao: BridgeDao, private val ndw: NDWApi) {

    fun getAllBridges(): List<Bridge> = dao.findAll()
        .map { entity -> entity.toBridge() }

    fun getBridgesByCity(city: String): List<Bridge> = dao.findByCity(city = city)
        .map { entity -> entity.toBridge() }

    fun refreshBridges(fetchType: FetchType): List<Bridge> {
        val bridges = ndw.fetchBridges()
        return saveBridges(bridges = bridges, fetchType = fetchType)
    }

    fun saveBridges(bridges: List<Bridge>, fetchType: FetchType): List<Bridge> {
        return dao.saveAll(bridges = bridges, type = fetchType).map { it.toBridge() }
    }

    fun deleteBridges(date: LocalDate): Boolean {
        val result = dao.deleteByDate(date = date)
        return result > 0
    }

    fun deleteBridge(bridge: Bridge): Boolean {
        val result = dao.delete(bridge = bridge)
        return result > 0
    }
}