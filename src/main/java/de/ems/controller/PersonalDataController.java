package de.ems.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("username") // Session-Attribut hinzufügen
public class PersonalDataController {
	
	 private final PersonalDataRepository repository;

    public PersonalDataController(PersonalDataRepository repository) {
        this.repository = repository;
    }
	
	 private static final java.util.List<String> SEASONS = Arrays.asList(
		        "Frühling", "Sommer", "Herbst", "Winter"
		    );

	  @GetMapping("/personal-data")
	    public String showPersonalDataForm(Model model) {
	        // PersonalData mit Benutzernamen aus Session initialisieren
	        PersonalData personalData = new PersonalData();
	        personalData.setUsername((String) model.getAttribute("username"));
	        
	        model.addAttribute("personalData", personalData);
	        model.addAttribute("seasons", SEASONS);
	        return "personal-data-form";
	    }


	 @PostMapping("/submit-personal-data")
	    public String submitPersonalData(
	        @Valid @ModelAttribute PersonalData personalData,
	        BindingResult bindingResult,
	        @ModelAttribute("username") String username) {
	        
		 	personalData.setUsername(username);
		 
	        if (bindingResult.hasErrors()) {
	            return "personal-data-form";
	        }
	     
	     // Benutzername aus Session zur Sicherheit überprüfen
//	     String sessionUsername = (String) session.getAttribute("username");
//	     personalData.setUsername(sessionUsername);
	     
	     // Speicherlogik
	     PersonalData existing = repository.findByUsername(personalData.getUsername());
	     if(existing != null) {
	         // Update bestehender Datensatz
//	         existing.updateFrom(personalData);
//	         repository.save(existing);
	     } else {
	         repository.save(personalData);
	     }
	     
	     return "redirect:/success";
	 }

    
}
