package com.me.init.initialproject.example.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

class UserRequest {
    @Schema(description = "유저아이디", example = "0")
    @field: NotEmpty(message = "아이디는 필수 입니다.")
    var userId: String? = null

    @Schema(description = "유저패스워드", example = "0")
    @field: NotEmpty(message = "비밀번호는 필수 입니다.")
    var userPassword: String? = null

    @Schema(description = "유저이름", example = "0")
    @field: NotEmpty(message = "비밀번호는 필수 입니다.")
    var userName: String? = null

    @Schema(description = "유저성별", example = "W")
    @field: NotEmpty(message = "비밀번호는 필수 입니다.")
    var userGender: String? = null

    @Schema(description = "유저생일", example = "940810")
    @field: NotEmpty(message = "비밀번호는 필수 입니다.")
    var userStatus: String? = null
}