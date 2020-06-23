package com.matb.ordering.api.models.entities.base

import java.io.Serializable
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity : Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "INT")
    var id: Int? = null
}
