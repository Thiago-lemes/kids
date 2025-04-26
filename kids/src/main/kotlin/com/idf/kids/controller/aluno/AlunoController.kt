package com.idf.kids.controller.aluno

import com.idf.kids.dto.request.CadastroAlunoRequest
import com.idf.kids.dto.response.AlunoResponse
import com.idf.kids.service.aluno.AlunoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aluno")
class AlunoController(
    private val service: AlunoService
) {
    @PostMapping("/register")
    fun cadastrarAluno(@RequestBody request: CadastroAlunoRequest): AlunoResponse {
      return  service.cadastrar(request)
    }
}


