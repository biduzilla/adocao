package com.ricky.adocao.service.impl;

import com.ricky.adocao.exception.NotFoundExeption;
import com.ricky.adocao.models.Usuario;
import com.ricky.adocao.repository.UsuarioRepository;
import com.ricky.adocao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findById(Long userId) {
        return usuarioRepository.findById(userId).orElseThrow(NotFoundExeption::new);
    }

    @Override
    public void deleteById(Long userId) {
        usuarioRepository.deleteById(userId);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
