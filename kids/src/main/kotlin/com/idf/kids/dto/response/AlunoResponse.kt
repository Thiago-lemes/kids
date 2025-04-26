package com.idf.kids.dto.response

import com.idf.kids.entity.aluno.AlunoEntity
import com.idf.kids.entity.usuario.UsuarioEntity
import java.util.*

data class AlunoResponse(
    val nome: String,
    val dataNascimento: Date,
    val responsavel: UsuarioEntity,
    val obeservacao: String,
) {
    companion object {
        fun fromEntity(entity: AlunoEntity): AlunoResponse {
            return AlunoResponse(
                nome = entity.nome,
                dataNascimento = entity.dataNascimento,
                responsavel = entity.responsavel,
                obeservacao = entity.obeservacao
            )
        }
    }
}


