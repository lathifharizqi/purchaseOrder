package com.pgi.test_be.service.impl

import com.pgi.test_be.domain.dto.request.ReqCreatePoDto
import com.pgi.test_be.domain.dto.response.BaseResponse
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
        return BaseResponse(data = listPo)
    }
}