package com.idf.kids.repository

import com.idf.kids.entity.aluno.AlunoEntity
import com.idf.kids.entity.aula.AulaEntity
import com.idf.kids.entity.aula.RegistrosAulaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RegistrosAulaRepository : JpaRepository<RegistrosAulaEntity?, Long?> {
    fun existsByAulaAndAluno(aula: AulaEntity, aluno: AlunoEntity): Boolean
}