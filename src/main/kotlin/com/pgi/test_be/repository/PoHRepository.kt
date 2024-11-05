package com.pgi.test_be.repository

import com.pgi.test_be.domain.entity.PoHEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PoHRepository: JpaRepository<PoHEntity, Int> {
}