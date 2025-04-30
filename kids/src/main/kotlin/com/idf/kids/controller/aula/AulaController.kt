package com.idf.kids.controller.aula

import AulaResponse
import CadastroAulaRequest
import com.idf.kids.service.aula.AulaService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aula")
class AulaController(private val service: AulaService) {
    @PostMapping("/register")
    fun cadastrarAula(@RequestBody request: CadastroAulaRequest): List<AulaResponse> {
        return service.cadastrar(request)
    }
}