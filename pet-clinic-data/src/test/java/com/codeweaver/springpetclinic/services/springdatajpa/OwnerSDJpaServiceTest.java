package com.codeweaver.springpetclinic.services.springdatajpa;

import com.codeweaver.springpetclinic.models.Owner;
import com.codeweaver.springpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String SAVY = "savy";
    public static final long id = 1L;
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(id).lastName(SAVY).build();
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(2L).lastName("pitt").build());
        ownerSet.add(Owner.builder().id(3L).lastName("caprio").build());

        // Creating mock for findAll method of owner repository which is called inside this method
        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> all = ownerSDJpaService.findAll();
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(returnOwner));

        assertNotNull(ownerSDJpaService.findById(id));
        assertEquals(1L, ownerSDJpaService.findById(id).getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        assertNull(ownerSDJpaService.findById(id));
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(2L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = ownerSDJpaService.save(owner);

        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);

        // To verify ownerrepository delete method is called
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(id);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner savy = ownerSDJpaService.findByLastName(SAVY);

        assertEquals(SAVY, savy.getLastName());

        verify(ownerRepository).findByLastName(any());
    }
}