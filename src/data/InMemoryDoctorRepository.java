package data;
/*
 * InMemoryDoctorRepository.java
 * This is the in-memory implementation for DoctorRepository.
 * we store doctors in a HashMap (id -> Doctor) so find/delete are fast.
 * we also support sorting by name and searching by name.
 * Later, if we wanted a real database, we could replace this class without changing the business layer.
 */
import model.Doctor;

import java.util.*;

public class InMemoryDoctorRepository implements DoctorRepository {

    private final Map<Integer, Doctor> doctors = new HashMap<>();

    @Override
    public Doctor save(Doctor d) {
        doctors.put(d.getId(), d);
        return d;
    }

    @Override
    public Doctor update(Doctor d) {
        doctors.put(d.getId(), d);
        return d;
    }

    @Override
    public boolean delete(int id) {
        return doctors.remove(id) != null;
    }

    @Override
    public Doctor findById(int id) {
        return doctors.get(id);
    }

    @Override
    public long count() {
        return doctors.size();
    }

    @Override
    public List<Doctor> findAllByOrderByName() {
        List<Doctor> list = new ArrayList<>(doctors.values());
        list.sort(Comparator.comparing(
                Doctor::getName,
                String.CASE_INSENSITIVE_ORDER
        ));
        return list;
    }

    @Override
    public List<Doctor> findByName(String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctors.values()) {
            if (d.getName() != null && d.getName().equalsIgnoreCase(name)) {
                result.add(d);
            }
        }
        return result;
    }
}
