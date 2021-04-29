package com.codeweaver.springpetclinic.repositories;

import com.codeweaver.springpetclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
