package com.codeweaver.springpetclinic.repositories;

import com.codeweaver.springpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
