package com.me.init.initialproject.example.infrastructure.book

import com.me.init.initialproject.example.entity.Book
import com.me.init.initialproject.example.entity.QBook.book
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class BookRepositorySupport(
    val queryFactory: JPAQueryFactory,
) : QuerydslRepositorySupport(Book::class.java) {

    fun findByTitle(title: String): MutableList<Book> {
        return queryFactory
            .selectFrom(book)
            .where(book.title.eq(title))
            .fetch()
    }
}