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
        Patient saved = app.savePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        Patient p = app.findPatientById(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public long count() {
        return app.countPatients();
    }

    @GetMapping
    public List<Patient> listSorted() {
        return app.listPatientsSortedByName();
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String name) {
        return app.findPatientsByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Patient patient) {
        if (patient.getId() != id) {
            return ResponseEntity.badRequest().body("Path ID and body ID do not match.");
        }
        Patient updated = app.updatePatient(patient);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean ok = app.deletePatient(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
