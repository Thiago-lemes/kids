package com.idf.kids.service.aluno

import com.idf.kids.Component.Utils
import com.idf.kids.dto.request.CadastroAlunoRequest
import com.idf.kids.dto.response.AlunoResponse
import com.idf.kids.repository.AlunoRepository
import org.springframework.stereotype.Service

@Service
class AlunoService (
    private val repository: AlunoRepository,
    private val ultis: Utils
){
    fun cadastrar(request: CadastroAlunoRequest): AlunoResponse {
        val aluno = repository.save(request.toEntity(ultis.usuarioLogado()))
        return AlunoResponse.fromEntity(aluno)
    }
}