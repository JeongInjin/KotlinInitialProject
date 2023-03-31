package com.bcg.funble.core.exampletdd.member

import com.bcg.funble.core.exampletdd.domain.Member
import com.bcg.funble.core.exampletdd.domain.Study

interface MemberService {
    fun findById(memberId: Long): Member?

    fun validate(memberId: Long)

    fun notify(newStudy: Study)

    fun notify(member: Member)
}
