package com.ricky.adocao.repository

import com.ricky.adocao.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository:JpaRepository<Usuario,String>{
}