package com.moira.thebyte.domain.question.dto.request

import com.moira.thebyte.global.jpa.entity.Question
import com.moira.thebyte.global.jpa.entity.User
import org.springframework.web.multipart.MultipartFile

data class QuestionAddRequest(
    private val title: String,
    private val content: String,
    private val aiAnswer: Boolean,
    val files: List<MultipartFile> = emptyList()
) {
    fun toQuestion(questionBy: User): Question {
        return Question(
            questionBy = questionBy,
            questionTo = null,
            title = this.title,
            content = this.content,
            aiAnswer = this.aiAnswer
        )
    }
}