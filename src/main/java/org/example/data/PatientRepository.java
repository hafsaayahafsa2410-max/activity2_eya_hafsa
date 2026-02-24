package org.example.data;

import org.example.model.Patient;
import java.util.List;

/**
 * Patient Repository Interface
 * Extends the generic CrudRepository and adds patient-specific query methods
 */
public interface PatientRepository extends CrudRepository<Patient> {

    /**
     * Find all patients ordered by name
     * @return List of patients sorted alphabetically by name
     */
    List<Patient> findAllByOrderByName();

    /**
     * Find patients by name (case-insensitive)
     * @param name The patient name to search for
     * @return List of patients matching the name
     */
    List<Patient> findByName(String name);

    /**
     * Get all patients
     * @return List of all patients
     */
    List<Patient> getAllPatients();
}