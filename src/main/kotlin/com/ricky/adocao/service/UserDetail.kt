package com.ricky.adocao.service

import com.ricky.adocao.models.Usuario
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = usuario.roles

    override fun getPassword() = usuario.senha

    override fun getUsername() = usuario.login

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}