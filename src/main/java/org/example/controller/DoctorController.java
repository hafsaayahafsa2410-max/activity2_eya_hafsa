package org.example.controller;

import org.example.app.AppFacade;
import org.example.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final AppFacade app;

    public DoctorController(AppFacade app) {
        this.app = app;
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(app.saveDoctor(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable int id) {
        Doctor d = app.findDoctorById(id);
        return (d != null) ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public long count() { return app.countDoctors(); }

    @GetMapping
    public List<Doctor> listSorted() { return app.listDoctorsSortedByName(); }

    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String name) { return app.findDoctorsByName(name); }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable int id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        return ResponseEntity.ok(app.updateDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return app.deleteDoctor(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{doctorId}/patients/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPatientToDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
        app.assignPatientToDoctor(doctorId, patientId);
    }

    @DeleteMapping("/{doctorId}/patients/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePatientFromDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
        app.removePatientFromDoctor(doctorId, patientId);
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<?>> getPatientsOfDoctor(@PathVariable int doctorId) {
        Doctor d = app.findDoctorById(doctorId);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(app.findPatientsByDoctor(doctorId));
    }
}