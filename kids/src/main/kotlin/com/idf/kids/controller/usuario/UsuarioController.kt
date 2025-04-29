package com.idf.kids.controller.usuario


import com.idf.kids.dto.request.CadastroUsuarioRequest
import com.idf.kids.dto.response.UsuarioResponse
import com.idf.kids.service.user.UsuarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("/register")
    fun cadastrarUsuario(@RequestBody request: CadastroUsuarioRequest): UsuarioResponse {
        return usuarioService.cadastrar(request)
    }
}
