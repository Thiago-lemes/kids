package com.idf.kids.service.User

import com.idf.kids.repository.UsuarioRepository
import com.idf.kids.security.UsuarioDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UsuarioDetailsService(
    private val usuarioRepository: UsuarioRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UsuarioDetails? {
        val usuario = usuarioRepository.findByEmail(username)
            ?.orElseThrow { UsernameNotFoundException("Usuário não encontrado com email: $username") }
        return UsuarioDetails(usuario!!)
    }
}