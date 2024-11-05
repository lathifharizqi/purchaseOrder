package com.pgi.test_be.controller

import com.pgi.test_be.domain.dto.request.ReqCreatePoDto
import com.pgi.test_be.service.PoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/po")
class PoController(
    val poService: PoService
) {

    @PostMapping("/create")
    fun createPo(
        @RequestBody reqCreatePoDto: ReqCreatePoDto
    ): ResponseEntity<Any>{
        val res = poService.createPo(reqCreatePoDto)
        return ResponseEntity.ok(res)
    }
}