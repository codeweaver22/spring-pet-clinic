package com.codeweaver.springpetclinic.controllers;

import com.codeweaver.springpetclinic.models.Pet;
import com.codeweaver.springpetclinic.models.Visit;
import com.codeweaver.springpetclinic.services.PetService;
import com.codeweaver.springpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VisitController {
    private static final String CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = Visit.builder().build();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initVisitForm(@PathVariable Long petId, Model model) {
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processVisitForm(Visit visit, BindingResult result) {
        if (result.hasErrors())
            return CREATE_OR_UPDATE_VISIT_FORM;
        else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

}
