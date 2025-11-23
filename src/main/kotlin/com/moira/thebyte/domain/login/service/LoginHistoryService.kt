package com.moira.thebyte.domain.login.service

import com.moira.thebyte.global.exception.ErrorCode
import com.moira.thebyte.global.jpa.entity.LoginHistory
import com.moira.thebyte.global.jpa.entity.User
import com.moira.thebyte.global.jpa.repository.LoginHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class LoginHistoryService(
    private val loginHistoryRepository: LoginHistoryRepository
) {
    /**
     * 로그인 성공 기록 저장
     */
    @Transactional(propagation = Propagation.REQUIRED)
    fun saveSuccessHistory(user: User, ipAddress: String, userAgent: String) {
        val loginHistory = LoginHistory(
            user = user,
            success = true,
            failureReason = null,
            ipAddress = ipAddress,
            userAgent = userAgent
        )
        loginHistoryRepository.save(loginHistory)
    }

    /**
     * 로그인 실패 기록 저장
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun saveFailureHistory(user: User, ipAddress: String, userAgent: String) {
        val loginHistory = LoginHistory(
            user = user,
            success = false,
            failureReason = ErrorCode.LOGIN_ERROR.name,
            ipAddress = ipAddress,
            userAgent = userAgent
        )
        loginHistoryRepository.save(loginHistory)
    }
}