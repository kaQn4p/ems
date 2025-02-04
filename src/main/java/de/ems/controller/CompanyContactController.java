package de.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.ems.model.CompanyContact;
import jakarta.validation.Valid;

@Controller
public class CompanyContactController {

    @GetMapping("/company-contact")
    public String showCompanyContactForm(Model model) {
        model.addAttribute("companyContact", new CompanyContact());
        return "company-data-form";
    }

    @PostMapping("/submit-company-contact")
    public String submitCompanyContact(@Valid @ModelAttribute("companyContact") CompanyContact companyContact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company-data-form";
        }
        // Verarbeitung der Daten
        return "redirect:/next-step"; // Weiterleitung zur nächsten Seite
    }
}
