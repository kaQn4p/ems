package de.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.ems.model.Model;

@Controller
public class InfoController {

//	@GetMapping("/info")
    @GetMapping("/")
    public String showInfoPage(Model model) {

    	return "info";
    }
}
