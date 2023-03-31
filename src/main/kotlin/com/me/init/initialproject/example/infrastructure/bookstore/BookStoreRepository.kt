package com.me.init.initialproject.example.infrastructure.bookstore

import com.me.init.initialproject.example.entity.BookStore
import org.springframework.data.jpa.repository.JpaRepository

interface BookStoreRepository : JpaRepository<BookStore, Long> {
}