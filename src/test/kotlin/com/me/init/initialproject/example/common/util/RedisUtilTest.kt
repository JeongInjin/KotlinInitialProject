package com.me.init.initialproject.example.common.util

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.concurrent.TimeUnit

@SpringBootTest
@ExtendWith(SpringExtension::class)
class RedisUtilTest {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var redisUtil: RedisUtil

    @Test
    fun `should set and get value from Redis`() {
        // Arrange
        val key = "test:key"
        val value = "test-value"

        // Act
        redisUtil.setValue(key, value)
        val retrievedValue = redisUtil.getValue(key)

        // Assert
        assertThat(retrievedValue).isEqualTo(value)
    }

    @Test
    fun `should delete value from Redis`() {
        // Arrange
        val key = "test:key"
        val value = "test-value"
        redisUtil.setValue(key, value)

        // Act
        val deleteResult = redisUtil.deleteValue(key)

        // Assert
        assertThat(deleteResult).isTrue()
        assertThat(redisUtil.getValue(key)).isNull()
    }

    @Test
    fun `should not retrieve value from Redis after expiration time`() {
        // Arrange
        val key = "test:key"
        val value = "test-value"
        val expireTime = 1L

        // Act
        redisUtil.setValue(key, value, expireTime)
        Thread.sleep(expireTime * 1000 + 100L)
        val retrievedValue = redisUtil.getValue(key)

        // Assert
        assertThat(retrievedValue).isNull()
    }
}
