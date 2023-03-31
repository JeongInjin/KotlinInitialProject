package com.bcg.funble.core.exampletdd.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue
    var Id: Long? = null,
    var Email: String? = null,
) {


    companion object {
        fun fixture(
            id: Long? = 1L,
            email: String = "injin@email.com"
        ): Member {
            return Member(
                Id = id,
                Email = email
            )
        }
    }
}