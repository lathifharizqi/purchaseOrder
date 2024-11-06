package com.pgi.test_be.controller

import com.pgi.test_be.domain.dto.request.ReqCreatePoDto
import com.pgi.test_be.service.PoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping()
    fun getListPo(): ResponseEntity<Any>{
        val res = poService.getListPo()
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{id}")
    fun getPoById(
        @PathVariable id: Int
    ): ResponseEntity<Any>{
        val res = poService.getPoById(id)
        return ResponseEntity.ok(res)
    }

    @PostMapping("/{id}")
    fun updatePo(
        @PathVariable id: Int,
        @RequestBody reqCreatePoDto: ReqCreatePoDto
    ): ResponseEntity<Any>{
        val res = poService.updatePo(id, reqCreatePoDto)
        return ResponseEntity.ok(res)
    }

    @DeleteMapping("/{id}")
    fun deletePo(
        @PathVariable id: Int
    ): ResponseEntity<Any>{
        val res = poService.deletePo(id)
        return ResponseEntity.ok(res)
    }
}