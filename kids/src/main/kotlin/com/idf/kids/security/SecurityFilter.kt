package com.idf.kids.security

import com.idf.kids.entity.usuario.UsuarioDetails
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import com.idf.kids.repository.UsuarioRepository

@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val userRepository: UsuarioRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recuperarToken(request)
        token?.let { it ->
            val login = tokenService.validateToken(it)
            val usuarioEntity = userRepository.findByEmail(login)?.orElse(null)
            usuarioEntity?.let {
                val usuarioDetails = UsuarioDetails(it)
                val auth = UsernamePasswordAuthenticationToken(usuarioDetails, null, usuarioDetails.authorities)
                SecurityContextHolder.getContext().authentication = auth
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        return authHeader?.replace("Bearer ", "")
    }
}
