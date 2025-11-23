package com.moira.thebyte.global.jpa.entity

import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "user", schema = "THE_BYTE")
class User(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID = UUID.randomUUID(),

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: UserRole = UserRole.USER,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: UserStatus = UserStatus.NORMAL,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "nickname", nullable = false, unique = true)
    val nickname: String,

    @Column(name = "phone", nullable = false)
    val phone: String,

    @Column(name = "image_url", columnDefinition = "TEXT", nullable = true)
    val imageUrl: String? = null,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "refresh_token", columnDefinition = "TEXT", nullable = true)
    var refreshToken: String? = null,

    @Column(name = "last_login_at", nullable = true)
    var lastLoginAt: ZonedDateTime? = null
) : BaseEntity()