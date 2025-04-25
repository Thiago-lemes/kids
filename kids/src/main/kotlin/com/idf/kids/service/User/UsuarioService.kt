package com.idf.kids.service.User


import com.idf.kids.dto.request.CadastroUsuarioRequest
import com.idf.kids.dto.response.UsuarioResponse
import com.idf.kids.entity.UsuarioEntity
import com.idf.kids.repository.UsuarioRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun cadastrar(request: CadastroUsuarioRequest): UsuarioResponse {
        if (repository.findByEmail(request.email)?.isPresent == true) {
            throw IllegalArgumentException("E-mail já cadastrado")
        }
        val usuario = repository.save(request.toEntity(passwordEncoder))
        return UsuarioResponse.fromEntity(usuario)
    }
}
