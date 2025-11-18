package com.moira.thebyte.global.auth

import com.moira.thebyte.global.exception.ErrorCode
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class FilterErrorSender {
    fun sendErrorResponse(
        response: HttpServletResponse,
        errorCode: ErrorCode
    ) {
        response.status = errorCode.httpStatus.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val errorResponse = """
            {"message": "${errorCode.message}", "errorCode": "${errorCode.code}", "time": "${ZonedDateTime.now()}"}
        """.trimIndent()

        response.writer.write(errorResponse)
    }
}