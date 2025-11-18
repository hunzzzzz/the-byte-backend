package com.moira.thebyte.global.auth

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class CookieHandler {
    fun putRtkInCookie(response: HttpServletResponse, rtk: String?) {
        val cookie = ResponseCookie.from("refreshToken", rtk)
            .maxAge(60 * 60 * 24L)
            .path("/")
            .httpOnly(true)
            .secure(true)
            .sameSite("None")
            .build()

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
    }

    fun removeRtkFromCookie(response: HttpServletResponse) {
        val cookie = ResponseCookie.from("refreshToken")
            .maxAge(0)
            .path("/")
            .httpOnly(true)
            .secure(true)
            .sameSite("None")
            .build()

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
    }
}