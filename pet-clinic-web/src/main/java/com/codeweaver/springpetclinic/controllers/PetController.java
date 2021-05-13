package com.codeweaver.springpetclinic.controllers;

import com.codeweaver.springpetclinic.models.Owner;
import com.codeweaver.springpetclinic.models.Pet;
import com.codeweaver.springpetclinic.models.PetType;
import com.codeweaver.springpetclinic.services.OwnerService;
import com.codeweaver.springpetclinic.services.PetService;
import com.codeweaver.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initPetCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processPetCreationForm(Owner owner, Pet pet, Model model, BindingResult result) {
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initPetUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processPetUpdateForm(Pet pet, Owner owner, Model model, BindingResult result) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return CREATE_OR_UPDATE_PET_FORM;
        } else {
            owner.getPets().add(pet);
            pet.setId(pet.getId());
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
