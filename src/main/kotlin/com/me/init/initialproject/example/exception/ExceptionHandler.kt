package com.me.init.initialproject.example.exception

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    companion object : KLogging()

    /**
     * Parameter Validation Exception Handler
     * DTO 에 정의한 Validation 을 통과하지 못한 경우 처리.
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandle(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult),
            ErrorCode.INVALID_INPUT_VALUE.status)
}