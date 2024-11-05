package com.pgi.test_be.service

import com.pgi.test_be.domain.dto.request.ReqCreatePoDto
import com.pgi.test_be.domain.dto.response.BaseResponse

interface PoService {
    fun createPo(reqCreatePoDto: ReqCreatePoDto): BaseResponse<Any>
    fun getListPo(): BaseResponse<Any>
}