package com.idf.kids.service.aula

import AulaResponse
import CadastroAulaRequest
import com.idf.kids.Component.Utils
import com.idf.kids.entity.aula.AulaEntity
import com.idf.kids.entity.aula.SalasEnum
import com.idf.kids.repository.AulaRepository
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Service
class AulaService(
    private val repository: AulaRepository,
    private var ultis: Utils
) {
    fun cadastrar(request: CadastroAulaRequest): List<AulaResponse> {
        val responsavel = ultis.usuarioLogado()

        val cultoDeCeia = verificaSeEPrimeiraSemana(request.dataAula)

        val salasParaCadastrar = if (cultoDeCeia) {
            SalasEnum.entries.filterNot { it == SalasEnum.JUNIORES_1 || it == SalasEnum.JUNIORES_2 }
        } else {
            SalasEnum.entries
        }

        val aulasCriadas = salasParaCadastrar.map { sala ->
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

    fun verificaSeEPrimeiraSemana(data: LocalDate): Boolean {
        val primeiroDiaMes = data.withDayOfMonth(1)
        val primeiraQuinta = primeiroDiaMes.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
        val primeiroDomingo = primeiroDiaMes.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

        return data == primeiraQuinta || data == primeiroDomingo
    }
}