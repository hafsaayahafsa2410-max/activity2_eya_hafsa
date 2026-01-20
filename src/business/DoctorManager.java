package business;
/*
 * DoctorManager.java
 * This is where our “logic” for doctors lives.
 * The important part is: it talks to DoctorRepository (the interface), not the in-memory class directly.
 * It basically controls doctor operations and can be the place to add validation/rules later.
 */
import data.DoctorRepository;
import model.Doctor;

import java.util.List;

public class DoctorManager {
    private DoctorRepository repo;

    public DoctorManager(DoctorRepository repo) {
        this.repo = repo;
    }

    public Doctor save(Doctor a) { return repo.save(a); }
    public Doctor update(Doctor a) { return repo.update(a); }
    public boolean delete(int id) { return repo.delete(id); }
    public Doctor findById(int id) { return repo.findById(id); }
    public long count() { return repo.count(); }
    public List<Doctor> listSorted() { return repo.findAllByOrderByName(); }
    public List<Doctor> findByName(String name) { return repo.findByName(name); }
}
