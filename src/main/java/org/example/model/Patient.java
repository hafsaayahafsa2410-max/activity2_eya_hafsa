package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String aiSummary;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;

    public Patient() {}
    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addDoctor(Doctor d) {
        this.doctor = d;
        if (!d.getPatients().contains(this)) {
            d.getPatients().add(this);
        }
    }

    public void removeDoctor() {
        if (this.doctor != null) {
            this.doctor.getPatients().remove(this);
            this.doctor = null;
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAiSummary() { return aiSummary; }
    public Doctor getDoctor() { return doctor; }
    public int getDoctorId() { return (doctor != null) ? doctor.getId() : 0; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setAiSummary(String aiSummary) { this.aiSummary = aiSummary; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}