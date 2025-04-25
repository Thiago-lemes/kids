package com.idf.kids.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import com.idf.kids.repository.UsuarioRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority

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
            val user: UserDetails? = userRepository.findByEmail(login)?.orElse(null)
            user?.let {
                val authorities = listOf(SimpleGrantedAuthority("ROLE_USER")) // mesmo role pra todo mundo
                val auth = UsernamePasswordAuthenticationToken(it, null, authorities)
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
