package org.example.ui;

import org.example.app.AppFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AppFacade app;

    public HomeController(AppFacade app) {
        this.app = app;
    }

    @GetMapping("/ui")
    public String home(Model model) {
        model.addAttribute("doctorCount", app.countDoctors());
        model.addAttribute("patientCount", app.countPatients());
        return "home";
    }
}
