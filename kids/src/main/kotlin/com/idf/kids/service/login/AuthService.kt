package com.idf.kids.service.login

import com.idf.kids.dto.request.LoginRequest
import com.idf.kids.dto.response.LoginResponse
import com.idf.kids.security.TokenService
import com.idf.kids.entity.usuario.UsuarioDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
) {
    fun loginAuth(request: LoginRequest): LoginResponse {
        if (request.email.isBlank() || request.senha.isBlank()) {
            throw IllegalArgumentException("E-mail e senha não podem ser vazios")
        }
        val authToken = UsernamePasswordAuthenticationToken(request.email, request.senha)
        val authentication = authenticationManager.authenticate(authToken)

        val usuarioDetails = authentication.principal as UsuarioDetails
        val usuario = usuarioDetails.getUsuario()
        val token = tokenService.generateToken(usuario)
        return LoginResponse(token)
    }
}