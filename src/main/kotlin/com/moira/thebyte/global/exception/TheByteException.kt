package com.moira.thebyte.global.exception

class TheByteException(val errorCode: ErrorCode) : RuntimeException(errorCode.message) {
}