package org.example.data;

import org.example.model.Patient;
import java.util.List;

public interface PatientRepository extends CrudRepository<Patient> {
    List<Patient> findAllByOrderByName();
    List<Patient> findByName(String name);
}
