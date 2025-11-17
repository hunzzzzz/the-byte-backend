package com.moira.thebyte.global.exception

import java.time.ZonedDateTime

data class ErrorResponse(
    val errorCode: ErrorCode,
    val message: String,
    val time: ZonedDateTime
)
