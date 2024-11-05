package com.pgi.test_be.domain.dto.response

import org.springframework.http.HttpStatus

data class BaseResponse<T>(
    val status: String? = "T",
    val message: String? = HttpStatus.OK.name,
    val data: T? = null
)
