package org.example.data;

import org.example.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
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
        list.sort(Comparator.comparing(Doctor::getName));
        return list;
    }

    @Override
    public List<Doctor> findByName(String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor d : doctors.values()) {
            if (d.getName().equalsIgnoreCase(name)) result.add(d);
        }
        return result;
    }
}
