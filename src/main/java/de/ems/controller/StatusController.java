package de.ems.controller;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import jakarta.transaction.Transactional;

@Controller
@SessionAttributes("username")
public class StatusController {

    private final PersonalDataRepository repository;
    
    private static final String UPLOAD_DIR = "uploads";

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
    @Transactional
    public String deleteProfile(@SessionAttribute("username") String username) {
	    repository.deleteByUsername(username);
	    
	    File uploadDir = new File(UPLOAD_DIR + "/" + username);
        if (uploadDir.exists()) {
        	try {
				FileUtils.deleteDirectory(uploadDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
      
        return "redirect:/";
    }
}
