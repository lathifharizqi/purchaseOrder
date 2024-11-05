package com.pgi.test_be.domain.dto.request

data class ReqCreateUserDto(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?
)
