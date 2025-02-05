package de.ems.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import jakarta.validation.Valid;

@Controller
public class PersonalDataController {
	
	 private final PersonalDataRepository repository;

    public PersonalDataController(PersonalDataRepository repository) {
        this.repository = repository;
    }
	
	 private static final java.util.List<String> SEASONS = Arrays.asList(
		        "Frühling", "Sommer", "Herbst", "Winter"
		    );

    @GetMapping("/personal-data")
    public String showPersonalDataForm(Model model, @ModelAttribute String id) {
    	
        model.addAttribute("personalData", new PersonalData((String)model.getAttribute("id")));
        model.addAttribute("seasons", SEASONS);
        return "personal-data-form";
    }

    @PostMapping("/submit-personal-data")
    public String submitPersonalData(@Valid PersonalData personalData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("seasons", SEASONS);
            return "personal-data-form";
        }
        
        // Datenverarbeitung
        PersonalData existing = repository.findByUsername(personalData.getId());
        if(existing != null) {
            // Update bestehenden Datensatz
//            existing.setField1(personalData.getField1());
//            existing.setField2(personalData.getField2());
            repository.save(existing);
        } else {
            // Neuen Datensatz erstellen
            repository.save(personalData);
        }
        
        
        return "redirect:/company-contact";
    }
    
}
