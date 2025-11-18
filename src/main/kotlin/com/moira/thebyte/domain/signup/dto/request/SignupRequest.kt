package com.moira.thebyte.domain.signup.dto.request

import com.moira.thebyte.global.jpa.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

data class SignupRequest(
    val name: String,
    val nickname: String,
    val phone: String,
    val email: String,
    val password: String,
) {
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            name = this.name,
            nickname = this.nickname,
            phone = this.phone,
            email = this.email,
            password = passwordEncoder.encode(this.password)
        )
    }
}
