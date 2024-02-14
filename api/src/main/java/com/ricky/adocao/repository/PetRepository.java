package com.ricky.adocao.repository;

import com.ricky.adocao.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
