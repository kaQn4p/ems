package de.ems.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("username") // Session-Attribut hinzufügen
public class InterestController {

	@Autowired
	private PersonalDataRepository repository;

	private static final java.util.List<String> SEASONS = Arrays.asList("Frühling", "Sommer", "Herbst", "Winter");

	public InterestController(PersonalDataRepository repository) {
		this.repository = repository;
	}

//	@GetMapping("/info")
	@GetMapping("/interest")
	public String showInfoPage(Model model) {

		PersonalData personalData = new PersonalData();
		personalData.setUsername((String) model.getAttribute("username"));

		model.addAttribute("personalData", personalData);
		model.addAttribute("seasons", SEASONS);

		return "interest";
	}

	@PostMapping("/submit-interest-data")
    public String submitPersonalData(@Valid @ModelAttribute PersonalData personalData,
                                     BindingResult bindingResult,
                                     @ModelAttribute("username") String username) {
        if (bindingResult.hasErrors()) {
            return "interest";
        }

        personalData.setUsername(username);
        personalData.setStatus(PersonalData.STATUS_WAITING_FOR_SIGNATURES);

        repository.save(personalData);

        return "status";
    }

}
