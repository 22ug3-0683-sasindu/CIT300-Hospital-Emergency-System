package hospital;

import hospital.model.Patient;
import hospital.model.TreatmentRecord;
import hospital.model.Visit;
import hospital.structures.EmergencyQueue;
import hospital.structures.PatientBST;
import hospital.structures.TreatmentStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Main {

    private static final PatientBST patientRecords = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentHistory = new TreatmentStack();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientRecords.inOrderTraversal();
                case 5 -> dequeueForTreatment();
                case 6 -> displayQueue();
                case 7 -> completeTreatment();
                case 8 -> treatmentHistory.displayStack();
                case 9 -> addVisitToPatient();
                case 10 -> removeVisitFromPatient();
                case 11 -> searchVisitForPatient();
                case 12 -> displayPatientVisits();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM =====");
        System.out.println(" 1. Register new patient (BST + Queue)");
        System.out.println(" 2. Search patient by ID (BST)");
        System.out.println(" 3. Delete patient (BST)");
        System.out.println(" 4. Display all patients - ascending ID (BST in-order)");
        System.out.println(" 5. Call next patient for treatment (Dequeue)");
        System.out.println(" 6. Display emergency waiting queue");
        System.out.println(" 7. Complete treatment for a patient (Push to Stack)");
        System.out.println(" 8. Display treatment history (Stack)");
        System.out.println(" 9. Add visit record to a patient (Linked List)");
        System.out.println("10. Remove visit record from a patient (Linked List)");
        System.out.println("11. Search visit record for a patient (Linked List)");
        System.out.println("12. Display a patient's visit history (Linked List)");
        System.out.println(" 0. Exit");
    }
    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientRecords.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        emergencyQueue.enqueue(patient);
        System.out.println("Patient registered and added to the emergency queue.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientRecords.search(id);
        if (found == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + found);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientRecords.delete(id);
        System.out.println(deleted ? "Patient deleted." : "No patient found with ID " + id);
    }
    private static void dequeueForTreatment() {
        Patient next = emergencyQueue.dequeue();
        if (next != null) {
            System.out.println("Now treating: " + next);
        }
    }

    private static void displayQueue() {
        System.out.println("Patients currently waiting:");
        emergencyQueue.displayQueue();
    }

    private static void completeTreatment() {
        int id = readInt("Enter Patient ID whose treatment is complete: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Register them first.");
            return;
        }
        System.out.print("Enter treatment description: ");
        String description = scanner.nextLine();
        String timestamp = LocalDateTime.now().format(TIME_FORMAT);

        TreatmentRecord record = new TreatmentRecord(id, patient.getName(), description, timestamp);
        treatmentHistory.push(record);
        System.out.println("Treatment recorded for " + patient.getName() + ".");
    }

    private static void addVisitToPatient() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        System.out.print("Enter Visit Date (e.g. 2026-09-01): ");
        String date = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
        System.out.println("Visit added to " + patient.getName() + "'s history.");
    }

    private static void removeVisitFromPatient() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "No visit found with that ID.");
    }

    private static void searchVisitForPatient() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Found: " + visit : "No visit found with that ID.");
    }

    private static void displayPatientVisits() {
        Patient patient = findPatientOrWarn();
        if (patient == null) return;

        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    private static Patient findPatientOrWarn() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ".");
        }
        return patient;
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
