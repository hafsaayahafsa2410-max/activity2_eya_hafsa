package org.example.ui;

import org.example.app.AppFacade;
import org.example.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/patients")
public class PatientWebController {

    private final AppFacade app;

    public PatientWebController(AppFacade app) {
        this.app = app;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("patients", app.listPatientsSortedByName());
        model.addAttribute("count", app.countPatients());
        return "patients/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Patient patient) {
        app.savePatient(patient);
        return "redirect:/ui/patients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        Patient p = app.findPatientById(id);
        if (p == null) return "redirect:/ui/patients";
        model.addAttribute("patient", p);
        return "patients/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable int id, @ModelAttribute Patient patient) {
        patient.setId(id);
        app.updatePatient(patient);
        return "redirect:/ui/patients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        app.deletePatient(id);
        return "redirect:/ui/patients";
    }

    @GetMapping("/{id}/doctor")
    public String assignDoctorForm(@PathVariable int id, Model model) {
        Patient p = app.findPatientById(id);
        if (p == null) return "redirect:/ui/patients";
        model.addAttribute("patient", p);
        model.addAttribute("doctors", app.listDoctorsSortedByName());
        return "patients/assign-doctor";
    }

    @PostMapping("/{patientId}/doctor/assign")
    public String assignDoctor(@PathVariable int patientId, @RequestParam int doctorId) {
        app.assignDoctorToPatient(patientId, doctorId);
        return "redirect:/ui/patients";
    }

    @PostMapping("/{patientId}/doctor/remove")
    public String removeDoctor(@PathVariable int patientId) {
        app.removeDoctorFromPatient(patientId);
        return "redirect:/ui/patients";
    }
}
