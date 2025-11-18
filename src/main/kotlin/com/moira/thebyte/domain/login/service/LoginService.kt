package com.moira.thebyte.domain.login.service

import com.moira.thebyte.domain.login.dto.request.LoginRequest
import com.moira.thebyte.domain.login.dto.response.LoginResponse
import com.moira.thebyte.global.auth.CookieHandler
import com.moira.thebyte.global.auth.JwtProvider
import com.moira.thebyte.global.exception.ErrorCode
import com.moira.thebyte.global.exception.TheByteException
import com.moira.thebyte.global.jpa.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class LoginService(
    private val cookieHandler: CookieHandler,
    private val jwtProvider: JwtProvider,
    private val loginHistoryService: LoginHistoryService,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    @Transactional
    fun login(
        request: LoginRequest,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): LoginResponse {
        // [1] HttpServletRequest로부터 유저 접속 정보 추출
        val ipAddress = httpServletRequest.remoteAddr
        val userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT)

        // [2] 이메일로 유저 조회
        val user = userRepository.findUserByEmail(email = request.email)
            ?: throw TheByteException(ErrorCode.LOGIN_ERROR)

        // [3-1] 비밀번호가 일치하지 않으면, 로그인 실패 기록 저장
        // [3-2] 비밀번호가 일치하면,       로그인 성공 기록 저장
        if (!passwordEncoder.matches(request.password, user.password)) {
            loginHistoryService.saveFailureHistory(user = user, ipAddress = ipAddress, userAgent = userAgent)

            throw TheByteException(ErrorCode.LOGIN_ERROR)
        } else {
            loginHistoryService.saveSuccessHistory(user = user, ipAddress = ipAddress, userAgent = userAgent)
        }

        // [4] JWT 토큰 생성
        val tokens = jwtProvider.createTokens(user = user)

        // [5] RTK 저장
        user.refreshToken = tokens.rtk
        user.lastLoginAt = ZonedDateTime.now()

        // [6] ATK는 클라이언트에게 리턴, RTK는 쿠키에 담아서 전송
        cookieHandler.putRtkInCookie(response = httpServletResponse, rtk = tokens.atk)

        return LoginResponse(accessToken = tokens.atk)
    }
}