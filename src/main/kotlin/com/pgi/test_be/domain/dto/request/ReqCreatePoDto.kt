package com.pgi.test_be.domain.dto.request

data class ReqCreatePoDto(
    val createdBy: String?,
    val description: String?,
    val details: List<ReqPoDetailsDto>?,
)
