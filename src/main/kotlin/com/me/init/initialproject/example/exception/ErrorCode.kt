package com.me.init.initialproject.example.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    // common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C0001", "유효하지 않은 입력 값 입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C0002", "유효하지 않은 입력 형식 입니다."),
    INPUT_VALUE_DUPLICATION(HttpStatus.BAD_REQUEST, "C0003", "중복된 입력 값 입니다."),
    NOT_ALLOWED_FILE(HttpStatus.BAD_REQUEST, "C0004", "허용되지 않는 파일 형식입니다."),
    NOT_READABLE_VALUE(HttpStatus.BAD_REQUEST, "C0005", "허용되지 않은 값 입니다."),
    SOMETHING_WRONG(HttpStatus.BAD_REQUEST, "C0006", "잘못된 요청입니다."),
    FILE_ERROR(HttpStatus.BAD_REQUEST, "C0007", "파일 오류"),
}