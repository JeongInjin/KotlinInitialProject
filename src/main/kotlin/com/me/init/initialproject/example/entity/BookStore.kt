package com.me.init.initialproject.example.entity

import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("서점")
class BookStore(
    @Id @GeneratedValue
    var id: Long? = null,

    var name: String? = null,

    @OneToMany(mappedBy = "bookStore", cascade = [CascadeType.ALL], orphanRemoval = true)
    val books: MutableSet<Book> = mutableSetOf()
) {
    fun add(book: Book) {
        this.books.add(book)
        book.bookStore = this
    }

    fun remove(book: Book) {
        this.books.remove(book)
        book.bookStore = null
    }

    override fun toString(): String {
        return "BookStore(id=$id, name=$name, books=$books)"
    }
}
