package de.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import de.ems.service.WebuntisVerificationService;

@Controller
public class WebuntisVerificationController {

    @Autowired
    private WebuntisVerificationService webuntisService;

    @GetMapping("/verify-webuntis")
    public String showWebuntisVerificationForm(Model model) {
        return "webuntis-verification";
    }
    
    @PostMapping("/verify-webuntis")
    public String verifyWebuntis(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isVerified = webuntisService.verifyCredentials(username, password);
        
        if (isVerified) {
            return "redirect:/personal-data";
        } else {
//            model.addAttribute("error", "Die Anmeldung bei Webuntis war nicht erfolgreich. Bitte überprüfen Sie Ihre Eingaben.");
            return "webuntis-verification";
        }
    }
}

