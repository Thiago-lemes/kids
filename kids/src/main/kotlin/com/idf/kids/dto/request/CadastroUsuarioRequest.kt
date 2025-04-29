package com.idf.kids.dto.request

import com.idf.kids.entity.usuario.TipoUsuario
import com.idf.kids.entity.usuario.UsuarioEntity
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

data class CadastroUsuarioRequest(
    val nome: String,
    val email: String,
    val senha: String,
    val tipo: TipoUsuario
) {
    fun toEntity(passwordEncoder: PasswordEncoder) = UsuarioEntity(
        nome = nome.uppercase(Locale.getDefault()),
        email = email,
        senha = passwordEncoder.encode(senha),
        tipo = tipo
    )
}
