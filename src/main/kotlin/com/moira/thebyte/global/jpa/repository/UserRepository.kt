package com.moira.thebyte.global.jpa.repository

import com.moira.thebyte.global.jpa.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun existsUserByEmail(email: String): Boolean
    fun existsUserByNickname(nickname: String): Boolean
}