package com.idf.kids.dto.response

import com.idf.kids.entity.UsuarioEntity

data class UsuarioResponse(
    val id: Long,
    val nome: String,
    val email: String
){
    companion object {
        fun fromEntity(entity: UsuarioEntity): UsuarioResponse {
            return UsuarioResponse(
                id = entity.id,
                nome = entity.nome,
                email = entity.email
            )
        }
    }
}
