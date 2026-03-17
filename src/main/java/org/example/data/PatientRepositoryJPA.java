package org.example.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.model.Patient;
import java.util.List;

@Repository
public interface PatientRepositoryJPA extends JpaRepository<Patient, Integer> {

    List<Patient> findAllByOrderByName();

    List<Patient> findByName(String name);

    List<Patient> findByDoctor_Id(int doctorId);
}