package com.pgi.test_be.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UsersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    val id: Int? = null,
    @Column(name = "first_name", columnDefinition = "varchar(500)")
    val firstName: String? = null,
    @Column(name = "last_name", columnDefinition = "varchar(500)")
    val lastName: String? = null,
    @Column(name = "email", columnDefinition = "varchar")
    val email: String? = null,
    @Column(name = "phone", columnDefinition = "varchar")
    val phone: String? = null,
    @Column(name = "created_by", columnDefinition = "varchar")
    val createdBy: String? = null,
    @Column(name = "updated_by", columnDefinition = "varchar")
    val updatedBy: String? = null,
    @Column(name = "created_datetime")
    val createdDatetime: LocalDateTime? = null,
    @Column(name = "updated_datetime")
    val updatedDatetime: LocalDateTime? = null
)
