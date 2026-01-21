package org.example.business;
/*
 * PatientManager.java 
 * Same idea as DoctorManager, but for patients.
 * It depends on the PatientRepository interface only.
 * It handles patient CRUD + searching + sorting, and it’s the clean middle layer between UI and data.
 */
import org.example.data.PatientRepository;
import org.example.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientManager {
    private final PatientRepository repo;

    public PatientManager(PatientRepository repo) {
        this.repo = repo;
    }

    public Patient save(Patient p) { return repo.save(p); }
    public Patient update(Patient p) { return repo.update(p); }
    public boolean delete(int id) { return repo.delete(id); }
    public Patient findById(int id) { return repo.findById(id); }
    public long count() { return repo.count(); }
    public List<Patient> listSorted() { return repo.findAllByOrderByName(); }
    public List<Patient> findByName(String title) { return repo.findAllByOrderByName(); }
}
