package com.idf.kids.service.aula

import AulaResponse
import CadastroAulaRequest
import com.idf.kids.Component.Utils
import com.idf.kids.dto.request.registerAulaRequest
import com.idf.kids.dto.response.AlunoRegistradoAulaResponse
import com.idf.kids.entity.aula.AulaEntity
import com.idf.kids.entity.aula.RegistrosAulaEntity
import com.idf.kids.entity.aula.SalasEnum
import com.idf.kids.repository.AlunoRepository
import com.idf.kids.repository.AulaRepository
import com.idf.kids.repository.RegistrosAulaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Service
class AulaService(
    private val repository: AulaRepository,
    private var ultis: Utils,
    private val alunoRepository: AlunoRepository,
    private val registrosAulaRepository: RegistrosAulaRepository
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

    @Transactional
    fun registrarAula(request: registerAulaRequest): AlunoRegistradoAulaResponse {
        val usuario = ultis.usuarioLogado()

        val aluno = alunoRepository.findById(request.aluno)
            .orElseThrow { RuntimeException("Aluno não encontrado com ID: ${request.aluno}") }

        val aula = repository.findById(request.aula)
            .orElseThrow { RuntimeException("Aula não encontrada com ID: ${request.aula}") }

        if (registrosAulaRepository.existsByAulaAndAluno(aula!!, aluno!!)) {
            throw IllegalStateException("O aluno já está registrado nessa aula.")
        }
            verificaCapacidadeAula(aula)

        val registro = RegistrosAulaEntity(
            aula = aula,
            aluno = aluno,
            usuario = usuario
        )

        val registroSalvo = registrosAulaRepository.save(registro)

        return AlunoRegistradoAulaResponse.fromEntity(registroSalvo)
    }

   private fun verificaSeEPrimeiraSemana(data: LocalDate): Boolean {
        val primeiroDiaMes = data.withDayOfMonth(1)
        val primeiraQuinta = primeiroDiaMes.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
        val primeiroDomingo = primeiroDiaMes.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

        return data == primeiraQuinta || data == primeiroDomingo
    }

    private fun verificaCapacidadeAula(aula: AulaEntity) {
        if (aula.capacidade <= 0) {
            throw RuntimeException("A aula não possui vagas disponíveis.")
        }
        aula.capacidade -= 1
        repository.save(aula)
    }
}