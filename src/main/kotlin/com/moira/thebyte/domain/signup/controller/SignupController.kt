package com.moira.thebyte.domain.signup.controller

import com.moira.thebyte.domain.signup.dto.request.SignupRequest
import com.moira.thebyte.domain.signup.service.SignupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SignupController(
    private val signupService: SignupService
) {
    /**
     * 닉네임 중복 확인
     */
    @GetMapping("/api/signup/check/nickname")
    fun checkNickname(@RequestParam nickname: String): ResponseEntity<Nothing> {
        signupService.checkNickname(nickname)

        return ResponseEntity.ok(null)
    }

    /**
     * 이메일 중복 확인
     */
    @GetMapping("/api/signup/check/email")
    fun checkEmail(@RequestParam email: String): ResponseEntity<Nothing> {
        signupService.checkEmail(email)

        return ResponseEntity.ok(null)
    }

    /**
     * 회원가입
     */
    @PostMapping("/api/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<Nothing> {
        signupService.signup(request)

        return ResponseEntity.ok(null)
    }
}