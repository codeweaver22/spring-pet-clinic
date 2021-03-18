package com.codeweaver.springpetclinic.services;

import com.codeweaver.springpetclinic.models.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();
}
