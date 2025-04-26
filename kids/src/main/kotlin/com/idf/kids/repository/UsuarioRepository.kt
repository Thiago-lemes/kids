package com.idf.kids.repository

import com.idf.kids.entity.usuario.UsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository : JpaRepository<UsuarioEntity?, Long?> {
    fun findByEmail(email: String?): Optional<UsuarioEntity?>?
}