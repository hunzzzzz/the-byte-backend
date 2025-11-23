package com.moira.thebyte.global.jpa.component

import com.moira.thebyte.global.exception.ErrorCode
import com.moira.thebyte.global.exception.TheByteException
import com.moira.thebyte.global.jpa.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class EntityFinder(
    private val userRepository: UserRepository
) {
    fun findUserById(userId: UUID) =
        userRepository.findByIdOrNull(id = userId) ?: throw TheByteException(ErrorCode.USER_NOT_FOUND)
}