package com.bcg.funble.core.exampletdd.study

import com.bcg.funble.core.exampletdd.domain.Study
import org.springframework.data.jpa.repository.JpaRepository

interface StudyRepository : JpaRepository<Study, Long> {
}