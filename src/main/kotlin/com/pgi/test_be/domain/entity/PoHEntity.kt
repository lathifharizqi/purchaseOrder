package com.pgi.test_be.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "po_h")
data class PoHEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    val id: Int? = null,
    @Column(name = "datetime")
    val datetime: LocalDateTime? = null,
    @Column(name = "description", columnDefinition = "varchar(500)")
    val description: String? = null,
    @Column(name = "total_price", columnDefinition = "int")
    val totalPrice: Int? = null,
    @Column(name = "total_cost", columnDefinition = "int")
    val totalCost: Int? = null,
    @Column(name = "created_by", columnDefinition = "varchar")
    val createdBy: String? = null,
    @Column(name = "updated_by", columnDefinition = "varchar")
    val updatedBy: String? = null,
    @Column(name = "created_datetime")
    val createdDatetime: LocalDateTime? = null,
    @Column(name = "updated_datetime")
    val updatedDatetime: LocalDateTime? = null
)
