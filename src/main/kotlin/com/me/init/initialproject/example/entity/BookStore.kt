package com.me.init.initialproject.example.entity

import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("서점")
class BookStore(
    @Id @GeneratedValue
    var id: Long? = null,

    var name: String? = null,

    @OneToMany(mappedBy = "bookStore")
    val books: MutableSet<Book> = mutableSetOf()
) {
    fun add(book: Book) {
        this.books.add(book)
    }

    override fun toString(): String {
        return "BookStore(id=$id, name=$name, books=$books)"
    }
}