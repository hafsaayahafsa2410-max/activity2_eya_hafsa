package org.example.data;

import org.example.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepositoryJPA extends JpaRepository<Doctor, Integer> {
    List<Doctor> findAllByOrderByName();
    List<Doctor> findByName(String name);
    List<Doctor> findBySpecialty(String specialty);
}