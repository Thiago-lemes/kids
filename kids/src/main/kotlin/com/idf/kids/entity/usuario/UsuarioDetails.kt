package com.idf.kids.entity.usuario

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UsuarioDetails(private val usuario: UsuarioEntity) : UserDetails {

    override fun getUsername(): String = usuario.email

    override fun getPassword(): String = usuario.password

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return mutableListOf<GrantedAuthority>()
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
    fun getUsuario(): UsuarioEntity {
        return usuario
    }
}

