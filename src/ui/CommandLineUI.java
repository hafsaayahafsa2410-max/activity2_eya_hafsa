package ui;
/*
 * CommandLineUI.java
 * This is the console menu the user interacts with.
 * It prints options, reads input using Scanner, and calls AppFacade to do the real work.
 * The UI doesn’t touch repositories directly it only uses the facade.
 */
import app.AppFacade;
import model.Doctor;
import model.Patient;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI {
    private final AppFacade app;
    private final Scanner scanner;

    public CommandLineUI(AppFacade app) {
        this.app = app;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> doctorMenu();
                case 2 -> patientMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    //  MAIN MENU
    private void printMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1) Manage Doctors");
        System.out.println("2) Manage Patients");
        System.out.println("0) Exit");
    }

    //  DOCTOR MENU
    private void doctorMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== DOCTOR MENU ===");
            System.out.println("1) Save Doctor");
            System.out.println("2) Update Doctor");
            System.out.println("3) Delete Doctor");
            System.out.println("4) Find Doctor by ID");
            System.out.println("5) Count Doctors");
            System.out.println("6) List Doctors Sorted by Name");
            System.out.println("7) Search Doctors by Name");
            System.out.println("0) Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> saveDoctor();
                case 2 -> updateDoctor();
                case 3 -> deleteDoctor();
                case 4 -> findDoctorById();
                case 5 -> System.out.println("Doctor count: " + app.countDoctors());
                case 6 -> printList(app.listDoctorsSortedByName());
                case 7 -> searchDoctorsByName();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void saveDoctor() {
        int id = readInt("Doctor id: ");
        String name = readLine("Doctor name: ");
        String specialty = readLine("Doctor specialty: ");

        Doctor saved = app.saveDoctor(new Doctor(id, name, specialty));
        System.out.println("Saved: " + saved);
    }

    private void updateDoctor() {
        int id = readInt("Doctor id to update: ");
        String name = readLine("New name: ");
        String specialty = readLine("New specialty: ");

        Doctor updated = app.updateDoctor(new Doctor(id, name, specialty));
        System.out.println("Updated: " + updated);
    }

    private void deleteDoctor() {
        int id = readInt("Doctor id to delete: ");
        boolean ok = app.deleteDoctor(id);
        System.out.println(ok ? "Deleted Doctor " + id : "Doctor not found.");
    }

    private void findDoctorById() {
        int id = readInt("Doctor id: ");
        Doctor d = app.findDoctorById(id);
        System.out.println(d != null ? d : "Doctor not found.");
    }

    private void searchDoctorsByName() {
        String name = readLine("Name to search: ");
        List<Doctor> results = app.findDoctorsByName(name);
        printList(results);
    }

    //  PATIENT MENU
    private void patientMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== PATIENT MENU ===");
            System.out.println("1) Save Patient");
            System.out.println("2) Update Patient");
            System.out.println("3) Delete Patient");
            System.out.println("4) Find Patient by ID");
            System.out.println("5) Count Patients");
            System.out.println("6) List Patients Sorted by Name");
            System.out.println("7) Search Patients by Name");
            System.out.println("0) Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> savePatient();
                case 2 -> updatePatient();
                case 3 -> deletePatient();
                case 4 -> findPatientById();
                case 5 -> System.out.println("Patient count: " + app.countPatients());
                case 6 -> printList(app.listPatientsSortedByName());
                case 7 -> searchPatientsByName();
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void savePatient() {
        int id = readInt("Patient id: ");
        String name = readLine("Patient name: ");
        int age = readInt("Patient age: ");
        int doctorId = readInt("Doctor id (assigned): ");

        Patient saved = app.savePatient(new Patient(id, name, age, doctorId));
        System.out.println("Saved: " + saved);
    }

    private void updatePatient() {
        int id = readInt("Patient id to update: ");
        String name = readLine("New name: ");
        int age = readInt("New age: ");
        int doctorId = readInt("Doctor id (assigned): ");

        Patient updated = app.updatePatient(new Patient(id, name, age, doctorId));
        System.out.println("Updated: " + updated);
    }

    private void deletePatient() {
        int id = readInt("Patient id to delete: ");
        boolean ok = app.deletePatient(id);
        System.out.println(ok ? "Deleted Patient " + id : "Patient not found.");
    }

    private void findPatientById() {
        int id = readInt("Patient id: ");
        Patient p = app.findPatientById(id);
        System.out.println(p != null ? p : "Patient not found.");
    }

    private void searchPatientsByName() {
        String name = readLine("Name to search: ");
        List<Patient> results = app.findPatientsByName(name);
        printList(results);
    }


    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("(no results)");
            return;
        }
        for (T item : list) {
            System.out.println(item);
        }
    }
}
