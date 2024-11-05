package com.pgi.test_be.domain.dto.request

data class ReqUpdateUserDto(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?
)
