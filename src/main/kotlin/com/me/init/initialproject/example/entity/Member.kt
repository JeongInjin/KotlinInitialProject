package com.me.init.initialproject.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jdk.jfr.Description

@Entity
@Description("회원")
class Member {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(length = 100)
    var email: String? = null // 이메일

}