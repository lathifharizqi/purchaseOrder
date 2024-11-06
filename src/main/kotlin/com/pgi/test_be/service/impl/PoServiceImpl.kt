package com.pgi.test_be.service.impl

import com.pgi.test_be.domain.dto.request.ReqCreatePoDto
import com.pgi.test_be.domain.dto.response.BaseResponse
import com.pgi.test_be.domain.dto.response.ResPoDetailsDto
import com.pgi.test_be.domain.dto.response.ResPoDto
import com.pgi.test_be.domain.entity.PoDEntity
import com.pgi.test_be.domain.entity.PoHEntity
import com.pgi.test_be.repository.ItemRepository
import com.pgi.test_be.repository.PoDRepository
import com.pgi.test_be.repository.PoHRepository
import com.pgi.test_be.service.PoService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class PoServiceImpl(
    val poHRepository: PoHRepository,
    val poDRepository: PoDRepository,
    val itemRepository: ItemRepository
): PoService {

    override fun createPo(reqCreatePoDto: ReqCreatePoDto): BaseResponse<Any> {
        try {
            val totals = reqCreatePoDto.details?.mapNotNull {
                val item = itemRepository.findById(it.itemId).orElse(null)
                    ?: return BaseResponse(status = "F", message = "Item ${it.itemId} not found")
                it to item
            } ?: return BaseResponse(status = "F", message = "Failed add po details")

            val totalPrice = totals.sumOf { (detail, item) -> item.price!! * detail.quantity }
            val totalCost = totals.sumOf { (detail, item) -> item.cost!! * detail.quantity }

            val poH = PoHEntity(
                datetime = LocalDateTime.now(),
                description = reqCreatePoDto.description,
                createdBy = reqCreatePoDto.createdBy,
                updatedBy = reqCreatePoDto.createdBy,
                createdDatetime = LocalDateTime.now(),
                updatedDatetime = LocalDateTime.now(),
                totalPrice = totalPrice,
                totalCost = totalCost
            )
            val poHId = poHRepository.save(poH).id
            totals.forEach { (detail, item) ->
                val poD = PoDEntity(
                    pohId = poHId,
                    itemId = detail.itemId,
                    itemQty = detail.quantity,
                    itemPrice = item.price,
                    itemCost = item.cost
                )
                poDRepository.save(poD)
            }
            return BaseResponse(message = "Success create po")
        } catch (e: Exception) {
            return BaseResponse(status = "F", message = "Failed create po")
        }
    }

    override fun getListPo(): BaseResponse<Any> {
        val listPo = poHRepository.findAll()
        if (listPo.isEmpty()) return BaseResponse(status = "F", message = "Po not found")
        val response = mutableListOf<ResPoDto>()
        listPo.forEach { poH ->
            val details = poDRepository.findByPohId(poH.id!!)
            response.add(ResPoDto(
                id = poH.id,
                datetime = poH.datetime.toString(),
                description = poH.description,
                totalPrice = poH.totalPrice,
                totalCost = poH.totalCost,
                createdBy = poH.createdBy,
                updatedBy = poH.updatedBy,
                createdDatetime = poH.createdDatetime.toString(),
                updatedDatetime = poH.updatedDatetime.toString(),
                details = details.map {
                    ResPoDetailsDto(
                        id = it.id!!,
                        itemId = it.itemId!!,
                        quantity = it.itemQty!!,
                        price = it.itemPrice!!,
                        cost = it.itemCost!!
                    )
                }
            ))
        }
        return BaseResponse(data = response)
    }

    override fun getPoById(id: Int): BaseResponse<Any> {
        val poH = poHRepository.findById(id).getOrNull()
            ?: return BaseResponse(status = "F", message = "Po not found")
        val details = poDRepository.findByPohId(poH.id!!)
        val response = ResPoDto(
            id = poH.id,
            datetime = poH.datetime.toString(),
            description = poH.description,
            totalPrice = poH.totalPrice,
            totalCost = poH.totalCost,
            createdBy = poH.createdBy,
            updatedBy = poH.updatedBy,
            createdDatetime = poH.createdDatetime.toString(),
            updatedDatetime = poH.updatedDatetime.toString(),
            details = details.map {
                ResPoDetailsDto(
                    id = it.id!!,
                    itemId = it.itemId!!,
                    quantity = it.itemQty!!,
                    price = it.itemPrice!!,
                    cost = it.itemCost!!
                )
            }
        )
        return BaseResponse(data = response)
    }

    override fun updatePo(id: Int, reqCreatePoDto: ReqCreatePoDto): BaseResponse<Any> {
        val poH = poHRepository.findById(id).getOrNull()
            ?: return BaseResponse(status = "F", message = "Po not found")
        val totals = reqCreatePoDto.details?.mapNotNull {
            val item = itemRepository.findById(it.itemId).orElse(null)
                ?: return BaseResponse(status = "F", message = "Item ${it.itemId} not found")
            it to item
        } ?: return BaseResponse(status = "F", message = "Failed add po details")

        val totalPrice = totals.sumOf { (detail, item) -> item.price!! * detail.quantity }
        val totalCost = totals.sumOf { (detail, item) -> item.cost!! * detail.quantity }

        val update = poH.copy(
            description = reqCreatePoDto.description,
            updatedBy = reqCreatePoDto.createdBy,
            updatedDatetime = LocalDateTime.now(),
            totalPrice = totalPrice,
            totalCost = totalCost
        )
        try {
            poHRepository.save(update)
            val removePoD = poDRepository.findByPohId(id)
            poDRepository.deleteAll(removePoD)
            totals.forEach { (detail, item) ->
                val poD = PoDEntity(
                    pohId = id,
                    itemId = detail.itemId,
                    itemQty = detail.quantity,
                    itemPrice = item.price,
                    itemCost = item.cost
                )
                poDRepository.save(poD)
            }
        } catch (e: Exception) {
            return BaseResponse(status = "F", message = "Failed update po")
        }
        return BaseResponse(message = "Success update po")
    }

    override fun deletePo(id: Int): BaseResponse<Any> {
        val poH = poHRepository.findById(id).getOrNull()
            ?: return BaseResponse(status = "F", message = "Po not found")
        val poD = poDRepository.findByPohId(id)
        try {
            poDRepository.deleteAll(poD)
            poHRepository.delete(poH)
        } catch (e: Exception) {
            return BaseResponse(status = "F", message = "Failed delete po")
        }
        return BaseResponse(message = "Success delete po")
    }
}