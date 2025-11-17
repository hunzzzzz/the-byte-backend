package com.moira.thebyte.domain.signup.service

import com.moira.thebyte.domain.signup.dto.request.SignupRequest
import com.moira.thebyte.global.jpa.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignupService(
    private val userRepository: UserRepository
) {
    /**
     * 닉네임 중복 확인
     */
    @Transactional(readOnly = true)
    fun checkNickname(nickname: String) {
        if (userRepository.existsUserByNickname(nickname)) {
            // TODO: 예외 처리
        }
    }

    /**
     * 이메일 중복 확인
     */
    @Transactional(readOnly = true)
    fun checkEmail(email: String) {
        if (userRepository.existsUserByEmail(email)) {
            // TODO: 예외 처리
        }
    }

    /**
     * 회원가입
     */
    @Transactional
    fun signup(request: SignupRequest) {
        val user = request.toUser()

        userRepository.save(user)
    }
}