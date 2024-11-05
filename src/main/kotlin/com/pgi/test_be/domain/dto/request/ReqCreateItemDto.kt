package com.pgi.test_be.domain.dto.request

data class ReqCreateItemDto(
    val name: String?,
    val description: String?,
    val price: Int?,
    val cost: Int?,
)
