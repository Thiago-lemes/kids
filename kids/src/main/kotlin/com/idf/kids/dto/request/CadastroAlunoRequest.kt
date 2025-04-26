package com.idf.kids.dto.request

import com.idf.kids.entity.aluno.AlunoEntity
import com.idf.kids.entity.usuario.UsuarioEntity
import java.util.*

data class CadastroAlunoRequest(
    val nome: String,
    val dataNascimento: Date,
    val obeservacao: String,
){
    fun toEntity(responsavel: UsuarioEntity) = AlunoEntity(
        nome = nome,
        dataNascimento = dataNascimento,
        responsavel = responsavel,
        obeservacao = obeservacao,
    )
}