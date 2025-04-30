package com.idf.kids.repository

import com.idf.kids.entity.aula.AulaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AulaRepository : JpaRepository <AulaEntity?, Long?> {
}