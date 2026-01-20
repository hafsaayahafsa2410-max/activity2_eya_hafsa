package model;
/*
 * Patient.java
 * This class represents a Patient.
 * It stores basic patient info (id, name, age) and also doctorId to connect the patient to a doctor.
 * Same idea as Doctor: clean fields, getters/setters, and toString() for readable output.
 */
public class Patient {
    private int id;
    private String name;
    private int age;
    private int doctorId;

    public Patient(int id, String name, int age, int doctorId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.doctorId = doctorId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getDoctorId() { return doctorId; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', age=" + age + ", doctorId=" + doctorId + "}";
    }
}
