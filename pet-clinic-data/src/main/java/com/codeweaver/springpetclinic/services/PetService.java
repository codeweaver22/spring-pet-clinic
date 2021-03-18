package com.codeweaver.springpetclinic.services;

import com.codeweaver.springpetclinic.models.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet owner);

    Set<Pet> findAll();
}
