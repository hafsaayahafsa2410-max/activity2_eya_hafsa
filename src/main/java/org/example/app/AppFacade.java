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

    public Doctor saveDoctor(Doctor d) { return doctorManager.save(d); }
    public Doctor updateDoctor(Doctor d) { return doctorManager.update(d); }
    public boolean deleteDoctor(int id) { return doctorManager.delete(id); }
    public Doctor findDoctorById(int id) { return doctorManager.findById(id); }
    public long countDoctors() { return doctorManager.count(); }
    public List<Doctor> listDoctorsSortedByName() { return doctorManager.listSorted(); }
    public List<Doctor> findDoctorsByName(String name) { return doctorManager.findByName(name); }
    public List<Doctor> findDoctorsBySpecialty(String s) { return doctorManager.findBySpecialty(s); }

    public Patient savePatient(Patient p) { return patientManager.save(p); }
    public Patient updatePatient(Patient p) { return patientManager.update(p); }
    public boolean deletePatient(int id) { return patientManager.delete(id); }
    public Patient findPatientById(int id) { return patientManager.findById(id); }
    public long countPatients() { return patientManager.count(); }
    public List<Patient> listPatientsSortedByName() { return patientManager.listSorted(); }
    public List<Patient> findPatientsByName(String name) { return patientManager.findByName(name); }
    public List<Patient> findPatientsByDoctor(int doctorId) { return patientManager.findByDoctorId(doctorId); }

    public void assignPatientToDoctor(int doctorId, int patientId) {
        Doctor doctor = doctorManager.findById(doctorId);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found: " + doctorId);
        Patient patient = patientManager.findById(patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);
        doctor.addPatient(patient);
        doctorManager.update(doctor);
    }

    public void removePatientFromDoctor(int doctorId, int patientId) {
        Doctor doctor = doctorManager.findById(doctorId);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found: " + doctorId);
        Patient patient = patientManager.findById(patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);
        doctor.removePatient(patient);
        doctorManager.update(doctor);
    }

    public void assignDoctorToPatient(int patientId, int doctorId) {
        Patient patient = patientManager.findById(patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);
        Doctor doctor = doctorManager.findById(doctorId);
        if (doctor == null) throw new IllegalArgumentException("Doctor not found: " + doctorId);
        patient.addDoctor(doctor);
        patientManager.update(patient);
    }

    public void removeDoctorFromPatient(int patientId) {
        Patient patient = patientManager.findById(patientId);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);
        patient.removeDoctor();
        patientManager.update(patient);
    }
}