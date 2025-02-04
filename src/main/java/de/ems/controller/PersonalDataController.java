package de.ems.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import de.ems.model.PersonalData;
import jakarta.validation.Valid;

@Controller
public class PersonalDataController {

    @GetMapping("/personal-data")
    public String showPersonalDataForm(Model model) {
        model.addAttribute("personalData", new PersonalData());
        model.addAttribute("seasons", Arrays.asList("Frühling", "Sommer", "Herbst", "Winter"));
        return "personal-data-form";
    }

    @PostMapping("/submit-personal-data")
    public String submitPersonalData(@Valid PersonalData personalData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "personal-data-form";
        }
        // Hier können Sie die Daten speichern oder weiterverarbeiten
        return "redirect:/company-contact"; // Weiterleitung zur nächsten Seite
    }
    
}
