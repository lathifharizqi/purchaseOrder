package com.pgi.test_be.service

import com.pgi.test_be.domain.dto.request.ReqCreateItemDto
import com.pgi.test_be.domain.dto.request.ReqUpdateItemDto
import com.pgi.test_be.domain.dto.response.BaseResponse

interface ItemService {
    fun createItem(reqCreateItemDto: ReqCreateItemDto): BaseResponse<Any>
    fun updateItem(reqUpdateItemDto: ReqUpdateItemDto): BaseResponse<Any>
    fun deleteItem(id: Int): BaseResponse<Any>
    fun getListItem(): BaseResponse<Any>
    fun getItemById(id: Int): BaseResponse<Any>
}