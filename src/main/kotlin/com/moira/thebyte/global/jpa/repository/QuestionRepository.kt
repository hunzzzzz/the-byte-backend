package com.moira.thebyte.global.jpa.repository

import com.moira.thebyte.global.jpa.entity.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
}