package com.moira.thebyte.domain.profile.controller

import com.moira.thebyte.global.aop.UserPrincipal
import com.moira.thebyte.global.auth.dto.SimpleUserAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController {
    @GetMapping("/api/me/simple")
    fun getSimpleProfile(@UserPrincipal simpleUserAuth: SimpleUserAuth): ResponseEntity<SimpleUserAuth> {
        return ResponseEntity.ok(simpleUserAuth)
    }
}