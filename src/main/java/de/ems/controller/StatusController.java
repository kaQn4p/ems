package de.ems.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ems.model.PersonalData;
import de.ems.repository.PersonalDataRepository;
import org.springframework.core.io.Resource;

import jakarta.transaction.Transactional;

import org.springframework.core.io.UrlResource;

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
    
    @PostMapping("/uploadDocument")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                 @RequestParam("documentType") String documentType,
                                 @SessionAttribute("username") String username,
                                 RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            String fileName = username + "_" + documentType + ".pdf";
            Path uploadPath = Paths.get(UPLOAD_DIR, username);
            try {
                Files.createDirectories(uploadPath);
                Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                
                if (checkAllDocumentsUploaded(username)) {
                    PersonalData personalData = repository.findByUsername(username);
                    personalData.setStatus("CHECKING_INTEREST");
                    repository.save(personalData);
                    redirectAttributes.addFlashAttribute("message", "Alle Dokumente wurden hochgeladen. Der Status wurde aktualisiert.");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Dokument erfolgreich hochgeladen.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Fehler beim Hochladen des Dokuments.");
            }
        }
        return "redirect:/status";
    }

    private boolean checkAllDocumentsUploaded(String username) {
        String[] documentTypes = {"datenschutz", "ablaufplan", "absichtserklaerung", "motivationsschreiben"};
        for (String docType : documentTypes) {
            if (!checkFileExists(username, docType)) {
                return false;
            }
        }
        return true;
    }
  
    public boolean checkFileExists(String username, String documentType) {
        String fileName = username + "_" + documentType + ".pdf";
        Path filePath = Paths.get(UPLOAD_DIR, username, fileName);
        return Files.exists(filePath) && !Files.isDirectory(filePath);
    }

    @PostMapping("/deleteDocument")
    public String deleteDocument(@RequestParam("documentType") String documentType,
                                 @SessionAttribute("username") String username) {
        String fileName = username + "_" + documentType + ".pdf";
        Path filePath = Paths.get(UPLOAD_DIR, username, fileName);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/status";
    }

    @GetMapping("/download/{username}/{documentType}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String username,
                                                     @PathVariable String documentType) {
        Path filePath = Paths.get(UPLOAD_DIR, username, username + "_" + documentType + ".pdf");
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}
