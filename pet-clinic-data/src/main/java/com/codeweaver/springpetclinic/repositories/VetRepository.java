package com.codeweaver.springpetclinic.repositories;

import com.codeweaver.springpetclinic.models.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
