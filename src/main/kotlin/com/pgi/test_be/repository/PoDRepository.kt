package com.pgi.test_be.repository

import com.pgi.test_be.domain.entity.PoDEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PoDRepository: JpaRepository<PoDEntity, Int> {
}