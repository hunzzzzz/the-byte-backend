package com.moira.thebyte.domain.question.service

import com.moira.thebyte.domain.question.dto.request.QuestionAddRequest
import com.moira.thebyte.domain.question.dto.response.QuestionIdResponse
import com.moira.thebyte.global.auth.dto.SimpleUserAuth
import com.moira.thebyte.global.jpa.component.EntityFinder
import com.moira.thebyte.global.jpa.repository.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QuestionAddService(
    private val entityFinder: EntityFinder,
    private val questionRepository: QuestionRepository
) {
    /**
     * 질문 등록
     */
    @Transactional
    fun add(simpleUserAuth: SimpleUserAuth, request: QuestionAddRequest): QuestionIdResponse {
        // 변수 세팅
        val userId = UUID.fromString(simpleUserAuth.userId)
        val questionBy = entityFinder.findUserById(userId = userId)

        // 저장
        val question = request.toQuestion(questionBy = questionBy)
        val savedQuestion = questionRepository.save(question)

        // TODO : 파일 저장 로직 추후 구현

        // 새로 등록된 질문의 id를 리턴
        return QuestionIdResponse(questionId = savedQuestion.id ?: -1L)
    }
}