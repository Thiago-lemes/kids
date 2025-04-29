package com.idf.kids.controller.aluno

import com.idf.kids.dto.request.CadastroAlunoRequest
import com.idf.kids.dto.response.AlunoCadastradoResponse
import com.idf.kids.dto.response.AlunoResponse
import com.idf.kids.service.aluno.AlunoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aluno")
class AlunoController(
    private val service: AlunoService
) {
    @PostMapping("/register")
    fun cadastrarAluno(@RequestBody request: CadastroAlunoRequest): AlunoCadastradoResponse {
      return  service.cadastrar(request)
    }

    @GetMapping("/list")
    fun listarAlunosDoResponsavel(): List<AlunoResponse> {
        return service.buscarAlunosDoResponsavel()
    }
}


