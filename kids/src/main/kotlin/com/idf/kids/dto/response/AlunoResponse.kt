package com.idf.kids.dto.response

import com.idf.kids.entity.aluno.AlunoEntity
import java.util.*

data class AlunoResponse(
    val nome: String,
    val dataNascimento: Date,
    val observacao: String?
) {
    companion object {
        fun fromEntity(entity: AlunoEntity): AlunoResponse {
            return AlunoResponse(
                nome = entity.nome,
                dataNascimento = entity.dataNascimento,
                observacao = entity.obeservacao
            )
        }
    }
}
