package com.me.init.initialproject.mytest.example.libtest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Person(val name: String, val age: Int)

class JsonTest {

    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun `should encode and decode person object`() {
        val person = Person("John", 30)

        val encodeJson = objectMapper.writeValueAsString(person)
        val jsonString = """{"name":"John","age":30}"""
        assertThat(encodeJson).isEqualTo(jsonString)

        val decodeJson = objectMapper.readValue(jsonString, Person::class.java)
        assertThat(decodeJson).isEqualTo(person)
    }
}
