package de.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrivacyPoliceController {

	@GetMapping("/privacy-police")
    public String showPrivacyPoliceForm() {
        return "privacypolice";
    }

    @PostMapping("/submit-privacy-consent")
    public String submitPrivacyPolice(@RequestParam("privacyConsent") boolean privacyConsent) {
        if (!privacyConsent) {
            return "redirect:/privacypolice";
        }
        // Hier können Sie die Zustimmung speichern oder verarbeiten
        return "redirect:/verify-webuntis"; // Weiterleitung zur nächsten Seite
    }
}
