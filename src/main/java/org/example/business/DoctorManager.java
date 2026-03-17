package org.example.business;

import org.example.data.DoctorRepositoryJPA;
import org.example.model.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorManager {
    private final DoctorRepositoryJPA repo;

    public DoctorManager(DoctorRepositoryJPA repo) { this.repo = repo; }

    public Doctor save(Doctor d) { return repo.save(d); }
    public Doctor update(Doctor d) { return repo.save(d); }
    public boolean delete(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
    public Doctor findById(int id) { return repo.findById(id).orElse(null); }
    public long count() { return repo.count(); }
    public List<Doctor> listSorted() { return repo.findAllByOrderByName(); }
    public List<Doctor> findByName(String name) { return repo.findByName(name); }
    public List<Doctor> findBySpecialty(String s) { return repo.findBySpecialty(s); }
}