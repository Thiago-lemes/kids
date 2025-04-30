package com.idf.kids.entity.aula

import com.idf.kids.entity.usuario.UsuarioEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "aula")
data class AulaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    val culto: CultosEnum,

    @Enumerated(EnumType.STRING)
    val sala: SalasEnum,

    val capacidade: Int = 0,

    @Column(name = "data_aula")
    val dataAula: LocalDate,

    val ativa: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_pelo_professor", referencedColumnName = "id")
    val criadoPeloProfessor: UsuarioEntity
)