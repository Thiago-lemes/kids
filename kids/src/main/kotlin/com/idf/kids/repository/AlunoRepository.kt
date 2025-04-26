package com.idf.kids.repository

import com.idf.kids.entity.aluno.AlunoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AlunoRepository : JpaRepository<AlunoEntity?, Long?> {
}