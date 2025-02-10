package de.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;

@Controller
@SessionAttributes("username")
public class StatusController {

    private final PersonalDataRepository repository;

    public StatusController(PersonalDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/status")
    public String showStatusForm(Model model, @SessionAttribute("username") String username) {
        PersonalData personalData = repository.findByUsername(username);
        if (personalData == null) {
            return "redirect:/interest";
        }
        model.addAttribute("personalData", personalData);
        return "status";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfile(@SessionAttribute("username") String username) {
        repository.deleteByUsername(username);
        return "redirect:/";
    }
}
