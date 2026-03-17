package org.example.business;

import org.example.data.PatientRepositoryJPA;
import org.example.model.Patient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientManager {
    private final PatientRepositoryJPA repo;

    public PatientManager(PatientRepositoryJPA repo) { this.repo = repo; }

    public Patient save(Patient p) { return repo.save(p); }
    public Patient update(Patient p) { return repo.save(p); }
    public boolean delete(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
    public Patient findById(int id) { return repo.findById(id).orElse(null); }
    public long count() { return repo.count(); }
    public List<Patient> listSorted() { return repo.findAllByOrderByName(); }
    public List<Patient> findByName(String name) { return repo.findByName(name); }
    public List<Patient> findByDoctorId(int doctorId) {
        return repo.findByDoctor_Id(doctorId);
    }
}