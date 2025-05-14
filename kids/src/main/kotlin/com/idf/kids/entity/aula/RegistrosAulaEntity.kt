package com.idf.kids.entity.aula

import com.idf.kids.entity.aluno.AlunoEntity
import com.idf.kids.entity.usuario.UsuarioEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "registros_aula")
data class RegistrosAulaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", referencedColumnName = "id", nullable = false)
    val aula: AulaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id", nullable = false)
    val aluno: AlunoEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    val usuario: UsuarioEntity,

    @Column(name = "data_registro", nullable = false)
    val dataRegistro: LocalDateTime = LocalDateTime.now()
)
