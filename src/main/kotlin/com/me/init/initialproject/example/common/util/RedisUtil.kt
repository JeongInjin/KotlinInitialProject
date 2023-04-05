package com.me.init.initialproject.example.common.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil(private val redisTemplate: RedisTemplate<String, Any>) {

    fun setValue(key: String, value: Any, expireTime: Long = -1) {
        redisTemplate.opsForValue().set(key, value)
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS)
        }
    }

    fun getValue(key: String): String? {
        return redisTemplate.opsForValue().get(key) as String?
    }

    fun deleteValue(key: String): Boolean {
        return redisTemplate.delete(key)
    }
}
