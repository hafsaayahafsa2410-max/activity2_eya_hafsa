package data;
/*
 * DoctorRepository.java
 * This is the repository interface for Doctor.
 * It extends the generic CRUD operations and adds:
 * - findAllByOrderByName(): to list doctors sorted by name
 * - findByName(): to search doctors by name
 * The business layer talks to this interface only .
 */
import model.Doctor;
import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor> {
    List<Doctor> findAllByOrderByName();
    List<Doctor> findByName(String name);
}
