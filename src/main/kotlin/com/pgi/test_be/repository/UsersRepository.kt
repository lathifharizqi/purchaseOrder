package com.pgi.test_be.repository

import com.pgi.test_be.domain.entity.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<UsersEntity, Int>{

}