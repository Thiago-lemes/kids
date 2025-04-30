package com.idf.kids.entity.usuario

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "usuarios")
 class UsuarioEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    val email: String,

    @Column(name = "password")
    private val senha: String,

    @Enumerated(EnumType.STRING)
    val tipo: TipoUsuario
) : UserDetails {

    override fun getUsername() = email

    override fun getPassword() = senha

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true 

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}

