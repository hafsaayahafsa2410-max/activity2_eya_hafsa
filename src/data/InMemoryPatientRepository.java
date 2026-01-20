package data;


import model.Patient;

import java.util.*;

public class InMemoryPatientRepository implements PatientRepository {

    private final Map<Integer, Patient> patients = new HashMap<>();

    @Override
    public Patient save(Patient p) {
        patients.put(p.getId(), p);
        return p;
    }

    @Override
    public Patient update(Patient p) {
        patients.put(p.getId(), p);
        return p;
    }

    @Override
    public boolean delete(int id) {
        return patients.remove(id) != null;
    }

    @Override
    public Patient findById(int id) {
        return patients.get(id);
    }

    @Override
    public long count() {
        return patients.size();
    }

    @Override
    public List<Patient> findAllByOrderByName() {
        List<Patient> list = new ArrayList<>(patients.values());
        list.sort(Comparator.comparing(Patient::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }

    @Override
    public List<Patient> findByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patients.values()) {
            if (p.getName() != null && p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }
}
