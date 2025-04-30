package com.idf.kids.dto.response

import com.idf.kids.entity.aluno.AlunoEntity
import java.util.*

data class AlunoCadastradoResponse(
    val nome: String,
    val dataNascimento: Date,
    val responsavel: UsuarioSimplesResponse,
    val obeservacao: String,
) {
    companion object {
        fun fromEntity(entity: AlunoEntity): AlunoCadastradoResponse {
            return AlunoCadastradoResponse(
                nome = entity.nome,
                dataNascimento = entity.dataNascimento,
                responsavel =  UsuarioSimplesResponse.fromEntity(entity.responsavel),
                obeservacao = entity.obeservacao
            )
        }
    }
}


