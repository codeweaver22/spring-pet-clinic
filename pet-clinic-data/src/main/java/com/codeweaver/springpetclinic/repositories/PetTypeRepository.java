package com.codeweaver.springpetclinic.repositories;

import com.codeweaver.springpetclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
