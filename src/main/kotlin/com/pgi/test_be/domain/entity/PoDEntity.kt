package com.pgi.test_be.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "po_d")
data class PoDEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    val id: Int? = null,
    @Column(name = "poh_id", columnDefinition = "int")
    val pohId: Int? = null,
    @Column(name = "item_id", columnDefinition = "int")
    val itemId: Int? = null,
    @Column(name = "item_qty", columnDefinition = "int")
    val itemQty: Int? = null,
    @Column(name = "item_price", columnDefinition = "int")
    val itemPrice: Int? = null,
    @Column(name = "item_cost", columnDefinition = "int")
    val itemCost: Int? = null,
)
