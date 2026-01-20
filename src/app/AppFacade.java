package app;
/*
 * AppFacade.java
 * This is the one door the UI uses to access the whole application.
 * Instead of the UI calling managers/repositories directly, it calls AppFacade methods.
 * AppFacade then delegates to DoctorManager and PatientManager.
 * This keeps the UI simple and  the architecture clean.
 */
import business.DoctorManager;
import business.PatientManager;
import model.Doctor;
import model.Patient;

import java.util.List;

public class AppFacade {
    private final DoctorManager doctorManager;
    private final PatientManager patientManager;

    public AppFacade(DoctorManager doctorManager, PatientManager patientManager) {
        this.doctorManager = doctorManager;
        this.patientManager = patientManager;
    }

    //  Doctor operations
    public Doctor saveDoctor(Doctor d) { return doctorManager.save(d); }
    public Doctor updateDoctor(Doctor d) { return doctorManager.update(d); }
    public boolean deleteDoctor(int id) { return doctorManager.delete(id); }
    public Doctor findDoctorById(int id) { return doctorManager.findById(id); }
    public long countDoctors() { return doctorManager.count(); }
    public List<Doctor> listDoctorsSortedByName() { return doctorManager.listSorted(); }
    public List<Doctor> findDoctorsByName(String name) { return doctorManager.findByName(name); }

    //  Patient operations
    public Patient savePatient(Patient p) { return patientManager.save(p); }
    public Patient updatePatient(Patient p) { return patientManager.update(p); }
    public boolean deletePatient(int id) { return patientManager.delete(id); }
    public Patient findPatientById(int id) { return patientManager.findById(id); }
    public long countPatients() { return patientManager.count(); }
    public List<Patient> listPatientsSortedByName() { return patientManager.listSorted(); }
    public List<Patient> findPatientsByName(String name) { return patientManager.findByName(name); }
}
