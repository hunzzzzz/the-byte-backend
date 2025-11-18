package com.moira.thebyte.global.auth

import com.moira.thebyte.global.auth.dto.TokenResponse
import com.moira.thebyte.global.jpa.entity.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    @param:Value("\${jwt.secret.key}") private val secretKey: String,
    @param:Value("\${jwt.issuer}") private val issuer: String,
    @param:Value("\${jwt.expiration-time.atk}") private val expirationTimeOfAtk: Long,
    @param:Value("\${jwt.expiration-time.rtk}") private val expirationTimeOfRtk: Long,
) {
    private lateinit var key: SecretKey

    @PostConstruct
    fun init() {
        this.key = Keys.hmacShaKeyFor(
            Base64.getDecoder().decode(secretKey)
        )
    }

    private fun createToken(user: User, expirationTime: Long): String {
        val now = Date()
        val claims: MutableMap<String, Any> = HashMap()

        claims["email"] = user.email
        claims["role"] = user.role.name
        claims["nickname"] = user.name

        return Jwts.builder()
            .subject(user.id.toString())
            .claims(claims)
            .expiration(Date(now.time + expirationTime))
            .issuedAt(now)
            .issuer(issuer)
            .signWith(key)
            .compact()
    }

    private fun createAtk(user: User): String {
        return createToken(user, expirationTimeOfAtk)
    }

    private fun createRtk(user: User): String {
        return createToken(user, expirationTimeOfRtk)
    }

    fun createTokens(user: User): TokenResponse {
        val atk = this.createAtk(user)
        val rtk = this.createRtk(user)

        return TokenResponse(atk, rtk)
    }

    fun substringToken(value: String): String {
        return value.substring("Bearer ".length)
    }

    fun validateToken(token: String): Result<Claims> =
        runCatching { Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload }
}