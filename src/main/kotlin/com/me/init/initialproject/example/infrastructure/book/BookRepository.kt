package com.me.init.initialproject.example.infrastructure.book

import com.me.init.initialproject.example.entity.Book
import org.springframework.data.jpa.repository.JpaRepository


interface BookRepository : JpaRepository<Book, Long>