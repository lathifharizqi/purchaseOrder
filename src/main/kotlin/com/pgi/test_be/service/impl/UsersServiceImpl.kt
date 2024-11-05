package com.pgi.test_be.service.impl

import com.pgi.test_be.domain.dto.request.ReqCreateUserDto
import com.pgi.test_be.domain.dto.request.ReqUpdateUserDto
import com.pgi.test_be.domain.dto.response.BaseResponse
import com.pgi.test_be.domain.entity.UsersEntity
import com.pgi.test_be.repository.UsersRepository
import com.pgi.test_be.service.UsersService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class UsersServiceImpl(
    val usersRepository: UsersRepository
): UsersService {

    override fun createUser(reqCreateUserDto: ReqCreateUserDto): BaseResponse<Any> {
        val insert = UsersEntity(
            firstName = reqCreateUserDto.firstName,
            lastName = reqCreateUserDto.lastName,
            email = reqCreateUserDto.email,
            phone = reqCreateUserDto.phone,
            createdBy = "system",
            updatedBy = "system",
            createdDatetime = LocalDateTime.now(),
            updatedDatetime = LocalDateTime.now()
        )
        try {
            usersRepository.save(insert)
        }catch (
            e: Exception
        ){
            return BaseResponse(status = "F", message = "Failed create user")
        }
        return BaseResponse(message = "Success create user")
    }

    override fun updateUser(reqUpdateUserDto: ReqUpdateUserDto): BaseResponse<Any> {
        val user = usersRepository.findById(reqUpdateUserDto.id).getOrNull()
            ?: return BaseResponse(status = "F", message = "User not found")
        val update = user.copy(
            firstName = reqUpdateUserDto.firstName ?: user.firstName,
            lastName = reqUpdateUserDto.lastName ?: user.lastName,
            email = reqUpdateUserDto.email ?: user.email,
            phone = reqUpdateUserDto.phone ?: user.phone,
            updatedBy = "system",
            updatedDatetime = LocalDateTime.now()
        )
        try {
            usersRepository.save(update)
        }catch (
            e: Exception
        ){
            return BaseResponse(status = "F", message = "Failed update user")
        }
        return BaseResponse(message = "Success update user")
    }

    override fun deleteUser(id: Int): BaseResponse<Any> {
        val user = usersRepository.findById(id).getOrNull()
            ?: return BaseResponse(status = "F", message = "User not found")
        usersRepository.delete(user)
        return BaseResponse(message = "Success delete user")
    }

    override fun getListUser(): BaseResponse<Any> {
        val list = usersRepository.findAll()
        return BaseResponse(data = list)
    }

    override fun getUserById(id: Int): BaseResponse<Any> {
        val user = usersRepository.findById(id)
        if (user.isEmpty) {
            return BaseResponse(status = "F", message = "User not found")
        }
        return BaseResponse(data = user)
    }
}