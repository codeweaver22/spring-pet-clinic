package com.codeweaver.springpetclinic.repositories;

import com.codeweaver.springpetclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
