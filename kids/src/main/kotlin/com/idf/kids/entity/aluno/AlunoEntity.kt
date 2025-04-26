package com.idf.kids.entity.aluno

import com.idf.kids.entity.usuario.UsuarioEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "aluno")
data class AlunoEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @Column(name = "data_nascimento")
    val dataNascimento: Date,

    @ManyToOne
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id")
    val responsavel: UsuarioEntity,

    val obeservacao: String,
)