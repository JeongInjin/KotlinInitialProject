package com.me.init.initialproject.example.entity

import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("책")
class Book(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var title: String? = null,

    @ManyToOne
    var bookStore: BookStore? = null,
) {


    override fun toString(): String {
        return "Book(id=$id, title=$title, bookStore=${bookStore?.name})"
    }
}