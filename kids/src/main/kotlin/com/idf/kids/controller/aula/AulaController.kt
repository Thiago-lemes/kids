package com.idf.kids.controller.aula

import AulaResponse
import CadastroAulaRequest
import com.idf.kids.dto.request.registerAulaRequest
import com.idf.kids.dto.response.AlunoRegistradoAulaResponse
import com.idf.kids.service.aula.AulaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aula")
class AulaController(private val service: AulaService) {
    @PostMapping("/create")
    fun createAula(@RequestBody request: CadastroAulaRequest): ResponseEntity<List<AulaResponse>> {
        val response = service.cadastrar(request)
        return ResponseEntity.ok(response)
    }
    @PostMapping("/register")
    fun registerAula(@RequestBody request: registerAulaRequest): ResponseEntity<AlunoRegistradoAulaResponse> {
        val response = service.registrarAula(request)
        return ResponseEntity.ok(response)
    }
}