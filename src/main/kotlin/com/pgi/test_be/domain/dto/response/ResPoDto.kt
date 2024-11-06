package com.pgi.test_be.domain.dto.response

data class ResPoDto(
    val id: Int?,
    val datetime: String? = null,
    val description: String? = null,
    val totalPrice: Int? = null,
    val totalCost: Int? = null,
    val createdBy: String?,
    val updatedBy: String?,
    val createdDatetime: String?,
    val updatedDatetime: String?,
    val details: List<ResPoDetailsDto>? = null
)
