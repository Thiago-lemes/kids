package com.idf.kids.dto.response

import com.idf.kids.entity.usuario.UsuarioEntity

data class UsuarioSimplesResponse(
    val id: Long,
    val nome: String
) {
    companion object {
        fun fromEntity(entity: UsuarioEntity): UsuarioSimplesResponse {
            return UsuarioSimplesResponse(
                id = entity.id,
                nome = entity.nome
            )
        }
    }
}