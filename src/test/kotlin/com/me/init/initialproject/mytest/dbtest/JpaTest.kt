package com.me.init.initialproject.mytest.dbtest

import com.me.init.initialproject.example.entity.Book
import com.me.init.initialproject.example.entity.BookStore
import com.me.init.initialproject.example.infrastructure.book.BookRepository
import com.me.init.initialproject.example.infrastructure.book.BookRepositorySupport
import com.me.init.initialproject.example.infrastructure.bookstore.BookStoreRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class JpaTest @Autowired constructor(
    private val em: EntityManager,
    private val bookRepository: BookRepository,
    private val bookStoreRepository: BookStoreRepository,
    private val bookRepositorySupport: BookRepositorySupport,
//    private val bookStoreRepositorySupport: BookStoreRepositorySupport,
) {

    @BeforeEach
    fun init() {
        val bookStore = BookStore()
        bookStore.name = "서점 이름 1"
        val bookStoreSave: BookStore = bookStoreRepository.save(bookStore)

        val book = Book()
        book.title = "책 제목 1"
        book.bookStore = bookStore
        bookStore.add(book)
        bookRepository.save(book)
    }

    @Test
    fun jpaTest() {
        val bookStore = BookStore()
        bookStore.name = "서점 이름"

        val bookStoreSave: BookStore = bookStoreRepository.save(bookStore)

        val book = Book()
        book.title = "책 제목"
        book.bookStore = bookStore
        bookStore.add(book)
        val bookSave = bookRepository.save(book)
        em.flush()

        bookStoreRepository.findById(bookStoreSave.id!!)
        assertThat(bookStore.name).isEqualTo("서점 이름")
        assertThat(bookStore.books).contains(book)

        bookStoreRepository.findById(bookStoreSave.id!!)
        assertThat(book.title).isEqualTo("책 제목")
        assertThat(book.bookStore!!.name).isEqualTo("서점 이름")

        println(bookStoreSave.toString())
        println(bookSave.toString())
    }

    @Test
    fun querydslTest1() {
        val empty_entity: MutableList<Book> = bookRepositorySupport.findByTitle("책 제목 !@#$")
        assertThat(empty_entity).isEmpty()

        val find_entity: MutableList<Book> = bookRepositorySupport.findByTitle("책 제목 1")
        assertThat(find_entity).isNotNull
        val book = find_entity[0]
        assertThat(book.title).isEqualTo("책 제목 1")
        assertThat(book.bookStore).isNotNull
        assertThat(book.bookStore!!.name).isEqualTo("서점 이름 1")

        println(book.toString())
        println(book.bookStore.toString())
    }
}