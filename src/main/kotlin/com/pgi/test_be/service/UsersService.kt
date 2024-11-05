package com.pgi.test_be.service

import com.pgi.test_be.domain.dto.request.ReqCreateUserDto
import com.pgi.test_be.domain.dto.request.ReqUpdateUserDto
import com.pgi.test_be.domain.dto.response.BaseResponse

interface UsersService {
    fun createUser(reqCreateUserDto: ReqCreateUserDto): BaseResponse<Any>
    fun updateUser(reqUpdateUserDto: ReqUpdateUserDto): BaseResponse<Any>
    fun deleteUser(id: Int): BaseResponse<Any>
    fun getListUser(): BaseResponse<Any>
    fun getUserById(id: Int): BaseResponse<Any>
}