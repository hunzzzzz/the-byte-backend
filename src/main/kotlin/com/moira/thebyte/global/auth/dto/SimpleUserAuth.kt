package com.moira.thebyte.global.auth.dto

data class SimpleUserAuth(
    val userId: String,
    val email: String,
    val role: String,
    val nickname: String
)