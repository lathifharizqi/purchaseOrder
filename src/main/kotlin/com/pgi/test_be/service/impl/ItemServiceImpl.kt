package com.pgi.test_be.service.impl

import com.pgi.test_be.domain.dto.request.ReqCreateItemDto
import com.pgi.test_be.domain.dto.request.ReqUpdateItemDto
import com.pgi.test_be.domain.dto.response.BaseResponse
import com.pgi.test_be.domain.entity.ItemEntity
import com.pgi.test_be.repository.ItemRepository
import com.pgi.test_be.service.ItemService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class ItemServiceImpl(
    val itemRepository: ItemRepository
): ItemService{

    override fun createItem(reqCreateItemDto: ReqCreateItemDto): BaseResponse<Any> {
        val insert = ItemEntity(
            name = reqCreateItemDto.name,
            description = reqCreateItemDto.description,
            price = reqCreateItemDto.price,
            cost = reqCreateItemDto.cost,
            createdBy = "system",
            updatedBy = "system",
            createdDatetime = LocalDateTime.now(),
            updatedDatetime = LocalDateTime.now()
        )
        try {
            itemRepository.save(insert)
        }catch (
            e: Exception
        ){
            return BaseResponse(status = "F", message = "Failed create item")
        }
        return BaseResponse(message = "Success create item")
    }

    override fun updateItem(reqUpdateItemDto: ReqUpdateItemDto): BaseResponse<Any> {
        val item = itemRepository.findById(reqUpdateItemDto.id).getOrNull()
            ?: return BaseResponse(status = "F", message = "Item not found")
        val update = item.copy(
            name = reqUpdateItemDto.name ?: item.name,
            description = reqUpdateItemDto.description ?: item.description,
            price = reqUpdateItemDto.price ?: item.price,
            cost = reqUpdateItemDto.cost ?: item.cost,
            updatedBy = "system",
            updatedDatetime = LocalDateTime.now()
        )
        try {
            itemRepository.save(update)
        }catch (
            e: Exception
        ){
            return BaseResponse(status = "F", message = "Failed update item")
        }
        return BaseResponse(message = "Success update item")
    }

    override fun deleteItem(id: Int): BaseResponse<Any> {
        val item = itemRepository.findById(id).getOrNull()
            ?: return BaseResponse(status = "F", message = "Item not found")
        itemRepository.delete(item)
        return BaseResponse(message = "Success delete item")
    }

    override fun getListItem(): BaseResponse<Any> {
        val data = itemRepository.findAll()
        return BaseResponse(data = data)
    }

    override fun getItemById(id: Int): BaseResponse<Any> {
        val data = itemRepository.findById(id)
        if (data.isEmpty) {
            return BaseResponse(status = "F", message = "Item not found")
        }
        return BaseResponse(data = data)
    }
}