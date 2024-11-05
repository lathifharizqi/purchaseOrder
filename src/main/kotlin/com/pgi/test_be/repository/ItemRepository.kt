package com.pgi.test_be.repository

import com.pgi.test_be.domain.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<ItemEntity, Int> {
}