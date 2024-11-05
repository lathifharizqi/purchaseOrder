package com.pgi.test_be.controller

import com.pgi.test_be.domain.dto.request.ReqCreateUserDto
import com.pgi.test_be.domain.dto.request.ReqUpdateUserDto
import com.pgi.test_be.service.UsersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    val usersService: UsersService
) {
    @PostMapping("/create")
    fun createUser(
        @RequestBody reqCreateUserDto: ReqCreateUserDto
    ): ResponseEntity<Any> {
        val res = usersService.createUser(reqCreateUserDto)
        return ResponseEntity.ok(res)
    }

    @PostMapping("/update")
    fun updateUser(
        @RequestBody reqUpdateUserDto: ReqUpdateUserDto
    ): ResponseEntity<Any> {
        val res = usersService.updateUser(reqUpdateUserDto)
        return ResponseEntity.ok(res)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        val res = usersService.deleteUser(id)
        return ResponseEntity.ok(res)
    }

    @GetMapping()
    fun getListUser(): ResponseEntity<Any> {
        val res = usersService.getListUser()
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        val res = usersService.getUserById(id)
        return ResponseEntity.ok(res)
    }
}