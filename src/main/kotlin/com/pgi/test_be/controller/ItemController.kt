package com.pgi.test_be.controller

import com.pgi.test_be.domain.dto.request.ReqCreateItemDto
import com.pgi.test_be.domain.dto.request.ReqUpdateItemDto
import com.pgi.test_be.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
class ItemController(
    val itemService: ItemService
) {
    @PostMapping("/create")
    fun createItem(
        @RequestBody reqCreateItemDto: ReqCreateItemDto
    ): ResponseEntity<Any> {
        val res = itemService.createItem(reqCreateItemDto)
        return ResponseEntity.ok(res)
    }

    @PostMapping("/update")
    fun updateItem(
        @RequestBody reqUpdateItemDto: ReqUpdateItemDto
    ): ResponseEntity<Any> {
        val res = itemService.updateItem(reqUpdateItemDto)
        return ResponseEntity.ok(res)
    }

    @DeleteMapping("/{id}")
    fun deleteItem(
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        val res = itemService.deleteItem(id)
        return ResponseEntity.ok(res)
    }

    @GetMapping()
    fun getListItem(): ResponseEntity<Any> {
        val res = itemService.getListItem()
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{id}")
    fun getItemById(
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        val res = itemService.getItemById(id)
        return ResponseEntity.ok(res)
    }
}