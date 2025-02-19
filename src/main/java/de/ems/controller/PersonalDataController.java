package de.ems.controller;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import jakarta.transaction.Transactional;

@Controller
@SessionAttributes("username") // Session-Attribut hinzufügen
public class PersonalDataController {

	@Autowired
    private final PersonalDataRepository repository;
    
    private static final String UPLOAD_DIR = "uploads";

    public PersonalDataController(PersonalDataRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/participants")
    public String listPersonalData(Model model) {
        model.addAttribute("personalDataList", repository.findAll());
        return "participants";
    }


    @PostMapping("/participants/delete")
    @Transactional
    public String deleteParticipant(@SessionAttribute("username") String username) {
	    repository.deleteByUsername(username);
	    
	    File uploadDir = new File(UPLOAD_DIR + "/" + username);
        if (uploadDir.exists()) {
        	try {
				FileUtils.deleteDirectory(uploadDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
      
        return "redirect:/participants";
    }
    
    @GetMapping("/participants/edit/{username}")
    public String editParticipant(@PathVariable String username, Model model) {
        PersonalData participant = repository.findByUsername(username);
        model.addAttribute("participant", participant);
        return "edit-participant";
    }

    @PostMapping("/participants/update")
    public String updateParticipant(@ModelAttribute PersonalData participant) {
        repository.save(participant);
        return "redirect:/participants";
    }

    @GetMapping("/participants/cancel")
    public String cancelEdit() {
        return "redirect:/participants";
    }
}


