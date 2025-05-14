package com.idf.kids.dto.response

import AulaResponse
import com.idf.kids.entity.aula.RegistrosAulaEntity
import java.time.LocalDateTime

data class AlunoRegistradoAulaResponse(
    val id: Long,
    val dataRegistro: LocalDateTime,
    val aula: AulaResponse,
    val aluno: AlunoResponse,
    val nomeUsuarioResponsavel: String
) {
    companion object {
        fun fromEntity(entity: RegistrosAulaEntity): AlunoRegistradoAulaResponse {
            return AlunoRegistradoAulaResponse(
                id = entity.id,
                dataRegistro = entity.dataRegistro,
                aula = AulaResponse.fromEntity(entity.aula),
                aluno =  AlunoResponse.fromEntity(entity.aluno),
                nomeUsuarioResponsavel = entity.usuario.nome
            )
        }
    }
}
