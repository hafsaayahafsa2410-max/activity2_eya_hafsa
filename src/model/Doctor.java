package model;
/*
 * Doctor.java
 * This class represents a Doctor in our system.
 * It’s basically the “data object” for a doctor (id, name, specialty).
 * we keep it simple with constructors, getters/setters, and toString() so it prints nicely in the CLI.
 */

public class Doctor {
    private int id;
    private String name;
    private String specialty;

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "', specialty='" + specialty + "'}";
    }
}
