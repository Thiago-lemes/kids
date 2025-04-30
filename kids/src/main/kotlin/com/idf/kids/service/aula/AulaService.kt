package com.idf.kids.service.aula

import AulaResponse
import CadastroAulaRequest
import com.idf.kids.Component.Utils
import com.idf.kids.entity.aula.AulaEntity
import com.idf.kids.entity.aula.SalasEnum
import com.idf.kids.repository.AulaRepository
import org.springframework.stereotype.Service

@Service
class AulaService(
    private val repository: AulaRepository,
    private var ultis: Utils
) {
    fun cadastrar(request: CadastroAulaRequest): List<AulaResponse> {
        val responsavel = ultis.usuarioLogado()

        val aulasCriadas = SalasEnum.entries.map { sala ->
            AulaEntity(
                culto = request.culto,
                sala = sala,
                capacidade = request.capacidade,
                dataAula = request.dataAula,
                ativa = false,
                criadoPeloProfessor = responsavel
            )
        }
        val aulasSalvas = repository.saveAll(aulasCriadas)
        return aulasSalvas.map { AulaResponse.fromEntity(it) }
    }
}