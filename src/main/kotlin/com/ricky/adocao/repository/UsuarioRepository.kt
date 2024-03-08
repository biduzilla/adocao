package com.ricky.adocao.repository

import com.ricky.adocao.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsuarioRepository:JpaRepository<Usuario,String>{

    fun findByLogin(login:String?):Usuario?


}