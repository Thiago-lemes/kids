package com.idf.kids.controller.login

import com.idf.kids.dto.request.LoginRequest
import com.idf.kids.dto.response.LoginResponse
import com.idf.kids.entity.usuario.UsuarioEntity
import com.idf.kids.entity.usuario.UsuarioDetails
import com.idf.kids.service.login.AuthService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody  request: LoginRequest): LoginResponse {
       return authService.loginAuth(request)
    }

    @GetMapping("/me")
    fun usuarioLogado(@AuthenticationPrincipal usuarioDetails: UsuarioDetails): UsuarioEntity {
        return usuarioDetails.getUsuario()
    }
}
