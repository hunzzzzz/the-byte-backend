package com.moira.thebyte.domain.question.controller

import com.moira.thebyte.domain.question.dto.request.QuestionAddRequest
import com.moira.thebyte.domain.question.dto.response.QuestionIdResponse
import com.moira.thebyte.domain.question.service.QuestionAddService
import com.moira.thebyte.global.aop.UserPrincipal
import com.moira.thebyte.global.auth.dto.SimpleUserAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class QuestionController(
    private val questionAddService: QuestionAddService
) {
    /**
     * 질문 등록
     */
    @PostMapping("/api/questions")
    fun add(
        @UserPrincipal simpleUserAuth: SimpleUserAuth,
        @RequestBody request: QuestionAddRequest
    ): ResponseEntity<QuestionIdResponse> {
        val questionIdResponse = questionAddService.add(simpleUserAuth, request)

        return ResponseEntity.ok(questionIdResponse)
    }
}