package com.idf.kids.Component

import com.idf.kids.entity.usuario.UsuarioEntity
import com.idf.kids.entity.usuario.UsuarioDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class Utils {
    fun usuarioLogado(): UsuarioEntity {
        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal

        if (principal is UsuarioDetails) {
            return principal.getUsuario()
        }
        throw IllegalStateException("Usuário não está autenticado")
    }
}