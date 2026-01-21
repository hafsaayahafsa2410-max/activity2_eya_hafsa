package org.example.app;

import org.example.business.DoctorManager;
import org.example.business.PatientManager;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
