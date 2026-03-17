package org.example.controller;

import org.example.app.AppFacade;
import org.example.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final AppFacade app;

    public PatientController(AppFacade app) {
        this.app = app;
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(app.savePatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        Patient p = app.findPatientById(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public long count() { return app.countPatients(); }

    @GetMapping
    public List<Patient> listSorted() { return app.listPatientsSortedByName(); }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String name) { return app.findPatientsByName(name); }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable int id, @RequestBody Patient patient) {
        patient.setId(id);
        return ResponseEntity.ok(app.updatePatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return app.deletePatient(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{patientId}/doctors/{doctorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDoctorToPatient(@PathVariable int patientId, @PathVariable int doctorId) {
        app.assignDoctorToPatient(patientId, doctorId);
    }

    @DeleteMapping("/{patientId}/doctors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDoctorFromPatient(@PathVariable int patientId) {
        app.removeDoctorFromPatient(patientId);
    }
}