package com.ricky.adocao.service;

import com.ricky.adocao.models.Usuario;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    Usuario findById(Long userId);

    void deleteById(Long userId);

    void delete(Usuario usuario);
}
