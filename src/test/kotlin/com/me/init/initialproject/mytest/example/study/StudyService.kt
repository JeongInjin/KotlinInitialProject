package com.bcg.funble.core.exampletdd.study

import com.bcg.funble.core.exampletdd.domain.Member
import com.bcg.funble.core.exampletdd.domain.Study
import com.bcg.funble.core.exampletdd.member.MemberService
import com.bcg.funble.core.exampletdd.study.StudyRepository

class StudyService(
    memberService: MemberService,
    repository: StudyRepository
) {
    private val memberService: MemberService?
    private val repository: StudyRepository?

    init {
        assert(memberService != null)
        assert(repository != null)
        this.memberService = memberService
        this.repository = repository
    }

    fun createNewStudy(memberId: Long, study: Study): Study {
        val member: Member? = memberService!!.findById(memberId)
        study.owner = member?: throw IllegalArgumentException("Member doesn't exist for id: $memberId")
        val newStudy = repository!!.save(study)
        memberService.notify(newStudy)
        memberService.notify(member!!)
        return newStudy
    }

    fun openStudy(study: Study): Study {
        study.open()
        val openedStudy = repository!!.save(study)
        memberService!!.notify(openedStudy)
        return openedStudy
    }
}