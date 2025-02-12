package de.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;

@Controller
@RequestMapping("/participants")
public class PersonalDataController {

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @GetMapping
    public String listPersonalData(Model model) {
        model.addAttribute("personalDataList", personalDataRepository.findAll());
        return "participants";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updatePersonalData(@RequestBody PersonalData personalData) {
        PersonalData existingData = personalDataRepository.findByUsername(personalData.getUsername());
        if (existingData != null) {
            // Aktualisieren Sie hier alle Felder außer dem Benutzernamen
            existingData.setLastName(personalData.getLastName());
            existingData.setFirstName(personalData.getFirstName());
            // Aktualisieren Sie hier weitere Felder
            personalDataRepository.save(existingData);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{username}")
    @ResponseBody
    public ResponseEntity<?> deletePersonalData(@PathVariable String username) {
        personalDataRepository.deleteByUsername(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResponseEntity<PersonalData> getPersonalData(@PathVariable String username) {
        PersonalData personalData = personalDataRepository.findByUsername(username);
        if (personalData != null) {
            return ResponseEntity.ok(personalData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

