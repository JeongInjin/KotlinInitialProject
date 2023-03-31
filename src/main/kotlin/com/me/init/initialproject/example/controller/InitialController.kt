package com.me.init.initialproject.example.controller

import com.me.init.initialproject.example.dto.UserRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import mu.KLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Initial Controller")
@RestController
@RequestMapping("/v1")
class InitialController {
    companion object : KLogging()

    @Operation(description = "테스트 컨트롤러 입니다.")
    @PostMapping("/test")
    fun testController(@Valid @RequestBody userRequest: UserRequest): String {
        KLogging().logger.info { "test controller 호출." }
        logger.info { "test controller 호출 2" }
        return "hello test"
    }

}