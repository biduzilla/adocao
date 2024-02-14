package com.ricky.adocao.service.impl;

import com.ricky.adocao.exception.NotFoundExeption;
import com.ricky.adocao.models.Pet;
import com.ricky.adocao.repository.PetRepository;
import com.ricky.adocao.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(NotFoundExeption::new);
    }

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        return petRepository.findAll(pageable);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long petId) {
        petRepository.deleteById(petId);
    }
}
