package com.idf.kids.security

import com.idf.kids.entity.UsuarioEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

//class UsuarioDetails(
//    private val usuario: UsuarioEntity
//) : UserDetails {
//
//    override fun getUsername() = usuario.email
//
//    override fun getPassword() = usuario.password
//
//    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()
//
//    override fun isAccountNonExpired() = true
//
//    override fun isAccountNonLocked() = true
//
//    override fun isCredentialsNonExpired() = true
//
//    override fun isEnabled() = true
//
//    fun getUsuario() = usuario
//}

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

