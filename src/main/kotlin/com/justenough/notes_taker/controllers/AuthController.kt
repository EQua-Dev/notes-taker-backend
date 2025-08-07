package com.justenough.notes_taker.controllers

import com.justenough.notes_taker.database.model.TokenPair
import com.justenough.notes_taker.database.requests.AuthRequest
import com.justenough.notes_taker.database.requests.RefreshRequest
import com.justenough.notes_taker.security.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody body: AuthRequest) {
        authService.register(body.email, body.password)
    }


    @PostMapping("/login")
    fun login(@RequestBody body: AuthRequest): TokenPair {
        return authService.login(body.email, body.password)
    }

    @PostMapping("/refresh-token")
    fun refresh(@RequestBody body: RefreshRequest): TokenPair {
        return authService.refresh(body.refreshToken)
    }


}