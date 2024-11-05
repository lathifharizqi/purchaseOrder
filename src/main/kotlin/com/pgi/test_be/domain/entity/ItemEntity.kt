package com.pgi.test_be.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "item")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    val id: Int? = null,
    @Column(name = "name", columnDefinition = "varchar(500)")
    val name: String? = null,
    @Column(name = "description", columnDefinition = "varchar(500)")
    val description: String? = null,
    @Column(name = "price", columnDefinition = "int")
    val price: Int? = null,
    @Column(name = "cost", columnDefinition = "int")
    val cost: Int? = null,
    @Column(name = "created_by", columnDefinition = "varchar")
    val createdBy: String? = null,
    @Column(name = "updated_by", columnDefinition = "varchar")
    val updatedBy: String? = null,
    @Column(name = "created_datetime")
    val createdDatetime: LocalDateTime? = null,
    @Column(name = "updated_datetime")
    val updatedDatetime: LocalDateTime? = null
)
