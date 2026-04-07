package org.example.ui;

import org.example.app.AppFacade;
import org.example.model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/doctors")
public class DoctorWebController {

    private final AppFacade app;

    public DoctorWebController(AppFacade app) {
        this.app = app;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("doctors", app.listDoctorsSortedByName());
        model.addAttribute("count", app.countDoctors());
        return "doctors/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Doctor doctor) {
        app.saveDoctor(doctor);
        return "redirect:/ui/doctors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        Doctor d = app.findDoctorById(id);
        if (d == null) return "redirect:/ui/doctors";
        model.addAttribute("doctor", d);
        return "doctors/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable int id, @ModelAttribute Doctor doctor) {
        doctor.setId(id);
        app.updateDoctor(doctor);
        return "redirect:/ui/doctors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        app.deleteDoctor(id);
        return "redirect:/ui/doctors";
    }

    @GetMapping("/{id}/patients")
    public String viewPatients(@PathVariable int id, Model model) {
        Doctor d = app.findDoctorById(id);
        if (d == null) return "redirect:/ui/doctors";
        model.addAttribute("doctor", d);
        model.addAttribute("assignedPatients", app.findPatientsByDoctor(id));
        model.addAttribute("allPatients", app.listPatientsSortedByName()
            .stream()
            .filter(p -> p.getDoctorId() == 0 || p.getDoctorId() == id)
            .toList());
        return "doctors/patients";
    }

    @PostMapping("/{doctorId}/patients/assign")
    public String assignPatient(@PathVariable int doctorId, @RequestParam int patientId) {
        app.assignPatientToDoctor(doctorId, patientId);
        return "redirect:/ui/doctors/" + doctorId + "/patients";
    }

    @PostMapping("/{doctorId}/patients/{patientId}/remove")
    public String removePatient(@PathVariable int doctorId, @PathVariable int patientId) {
        app.removePatientFromDoctor(doctorId, patientId);
        return "redirect:/ui/doctors/" + doctorId + "/patients";
    }
}
