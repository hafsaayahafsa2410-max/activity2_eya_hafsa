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

    // CREATE
    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        Doctor saved = app.saveDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable int id) {
        Doctor d = app.findDoctorById(id);
        return (d != null) ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    // COUNT
    @GetMapping("/count")
    public long count() {
        return app.countDoctors();
    }

    // LIST sorted
    @GetMapping
    public List<Doctor> listSorted() {
        return app.listDoctorsSortedByName();
    }

    // SEARCH by name
    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String name) {
        return app.findDoctorsByName(name);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Doctor doctor) {
        if (doctor.getId() != id) {
            return ResponseEntity.badRequest().body("Path ID and body ID do not match.");
        }
        Doctor updated = app.updateDoctor(doctor);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean ok = app.deleteDoctor(id);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
