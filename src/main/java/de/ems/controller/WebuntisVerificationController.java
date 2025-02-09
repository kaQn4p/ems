package de.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import ch.qos.logback.core.model.Model;
import de.ems.service.WebuntisVerificationService;

@Controller
public class WebuntisVerificationController {

    @Autowired
    private WebuntisVerificationService webuntisService;

    @GetMapping("/")
    public String showWebuntisVerificationForm(Model model) {
        return "verify-webuntis";
    }
    
    @PostMapping("/verify-webuntis")
    public String verifyWebuntis(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        boolean isVerified = webuntisService.verifyCredentials(username, password);
        
        if (isVerified) {
        	session.setAttribute("username", username);
            return "redirect:/interest";
        } else {
//            model.addAttribute("error", "Die Anmeldung bei Webuntis war nicht erfolgreich. Bitte überprüfen Sie Ihre Eingaben.");
            return "verify-webuntis";
        }
    }
}

