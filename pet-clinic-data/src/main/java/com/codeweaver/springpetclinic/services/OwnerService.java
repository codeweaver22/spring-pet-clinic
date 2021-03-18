package com.codeweaver.springpetclinic.services;

import com.codeweaver.springpetclinic.models.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
