package de.ems.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.ui.Model;
import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;

@Controller
@SessionAttributes("username") // Session-Attribut hinzufügen
public class InfoController {
	
	 private final PersonalDataRepository repository;
	 
	 private static final java.util.List<String> SEASONS = Arrays.asList(
		        "Frühling", "Sommer", "Herbst", "Winter"
		    );

	    public InfoController(PersonalDataRepository repository) {
	        this.repository = repository;
	    }

//	@GetMapping("/info")
    @GetMapping("/")
    public String showInfoPage(Model model) {

    	PersonalData personalData = new PersonalData();
        personalData.setUsername((String) model.getAttribute("username"));
        
        model.addAttribute("personalData", personalData);
        model.addAttribute("seasons", SEASONS);
    	
    	return "info";
    }
}
