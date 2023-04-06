package com.me.init.initialproject.mytest.dbtest

import com.me.init.initialproject.example.entity.Book
import com.me.init.initialproject.example.entity.BookStore
import com.me.init.initialproject.example.infrastructure.book.BookRepository
import com.me.init.initialproject.example.infrastructure.book.BookRepositorySupport
import com.me.init.initialproject.example.infrastructure.bookstore.BookStoreRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig


@SpringJUnitConfig
@DataJpaTest
class BookstoreAndBookEntityTest {

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var bookStoreRepository: BookStoreRepository

    @Autowired
    private lateinit var bookRepository: BookRepository

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
    fun `should create a new bookstore`() {
        // Given
        val bookstore = BookStore()
        bookstore.name = "새로운 서점"

        // When
        val savedBookstore = bookStoreRepository.save(bookstore)

        // Then
        em.flush()
        assertThat(savedBookstore.id).isNotNull()
        assertThat(savedBookstore.name).isEqualTo("새로운 서점")
    }

    @Test
    fun `should read a bookstore by id`() {
        // Given
        val savedBookstore = bookStoreRepository.findByName("서점 이름 1").first()

        // When
        val foundBookstore = bookStoreRepository.findById(savedBookstore.id!!)

        // Then
        em.flush()
        assertThat(foundBookstore).isNotEmpty
        assertThat(foundBookstore.get().id).isEqualTo(savedBookstore.id)
        assertThat(foundBookstore.get().name).isEqualTo(savedBookstore.name)
    }

    @Test
    fun `should update a bookstore`() {
        // Given
        val savedBookstore = bookStoreRepository.findByName("서점 이름 1").first()

        // When
        savedBookstore.name = "바뀐 서점 이름"
        val updatedBookstore = bookStoreRepository.save(savedBookstore)

        // Then
        em.flush()
        assertThat(updatedBookstore.id).isEqualTo(savedBookstore.id)
        assertThat(updatedBookstore.name).isEqualTo("바뀐 서점 이름")
    }

    @Test
    fun `should delete a bookstore`() {
        // Given
        val savedBookstore = bookStoreRepository.findByName("서점 이름 1").first()

        // When
        bookStoreRepository.delete(savedBookstore)

        // Then
        em.flush()
        val foundBookstore = bookStoreRepository.findById(savedBookstore.id!!)
        assertThat(foundBookstore).isEmpty
    }

    @Test
    fun `should create a new book`() {
        // Given
        val bookstore = bookStoreRepository.findByName("서점 이름 1").first()
        val book = Book()
        book.title = "새로운 책"
        book.bookStore = bookstore

        // When
        val savedBook = bookRepository.save(book)

        // Then
        em.flush()
        assertThat(savedBook.id).isNotNull()
        assertThat(savedBook.title).isEqualTo("새로운 책")
        assertThat(savedBook.bookStore).isEqualTo(bookstore)
    }

    @Test
    fun `should read a book by id`() {
        // Given
        val savedBook = bookRepository.findByTitle("책 제목 1").first()

        // When
        val foundBook = bookRepository.findById(savedBook.id!!)

        // Then
        em.flush()
        assertThat(foundBook).isNotEmpty
        assertThat(foundBook.get().id).isEqualTo(savedBook.id)
        assertThat(foundBook.get().title).isEqualTo(savedBook.title)
        assertThat(foundBook.get().bookStore?.id).isEqualTo(savedBook.bookStore?.id)
    }

    @Test
    fun `should update a book`() {
        // Given
        val savedBook = bookRepository.findByTitle("책 제목 1").first()

        // When
        savedBook.title = "바뀐 책 제목"
        val updatedBook = bookRepository.save(savedBook)

        // Then
        em.flush()
        assertThat(updatedBook.id).isEqualTo(savedBook.id)
        assertThat(updatedBook.title).isEqualTo("바뀐 책 제목")
    }

    @Test
    fun `should delete a book`() {
        // Given
        val savedBook = bookRepository.findByTitle("책 제목 1").first()
        assertThat(savedBook).isNotNull

        val bookStore = savedBook.bookStore!!
        bookStore.remove(savedBook) // remove book from bookStore's books set

        // When
        bookRepository.delete(savedBook)

        // Then
        em.flush()
        val foundBook = bookRepository.findById(savedBook.id!!)
        assertThat(foundBook).isEmpty
    }

}
