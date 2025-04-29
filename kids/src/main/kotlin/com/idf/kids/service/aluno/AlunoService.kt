package com.idf.kids.service.aluno

import com.idf.kids.Component.Utils
import com.idf.kids.dto.request.CadastroAlunoRequest
import com.idf.kids.dto.response.AlunoResponse
import com.idf.kids.exception.BusinessException
import com.idf.kids.repository.AlunoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AlunoService(
    private val repository: AlunoRepository,
    private var ultis: Utils
) {
    fun cadastrar(request: CadastroAlunoRequest): AlunoResponse {
        val responsavel = ultis.usuarioLogado();
        if (repository.existsByResponsavelIdAndNome(responsavel.id, request.nome)) {
            throw BusinessException(
                message = "Aluno '${request.nome}' já cadastrado",
                errorCode = "ALUNO_JA_EXISTE",
                status = HttpStatus.CONFLICT
            )
        }
        val alunoNovo = repository.save(request.toEntity(responsavel))
        return AlunoResponse.fromEntity(alunoNovo)
    }
}