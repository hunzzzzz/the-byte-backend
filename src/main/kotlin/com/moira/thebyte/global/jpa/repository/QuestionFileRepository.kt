package com.moira.thebyte.global.jpa.repository

import com.moira.thebyte.global.jpa.entity.QuestionFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuestionFileRepository : JpaRepository<QuestionFile, UUID> {
}