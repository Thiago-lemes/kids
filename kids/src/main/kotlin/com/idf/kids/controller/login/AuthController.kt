package com.idf.kids.controller.login

import com.idf.kids.dto.request.LoginRequest
import com.idf.kids.dto.response.LoginResponse
import com.idf.kids.service.login.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody  request: LoginRequest): LoginResponse {
       return authService.loginAuth(request)
    }
}
