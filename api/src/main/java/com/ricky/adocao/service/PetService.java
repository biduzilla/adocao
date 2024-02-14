package com.ricky.adocao.service;

import com.ricky.adocao.models.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetService {
    Pet save(Pet pet);

    Pet findById(Long id);

    Page<Pet> findAll(Pageable pageable);

    void delete(Pet pet);

    void deleteById(Long petId);
}
