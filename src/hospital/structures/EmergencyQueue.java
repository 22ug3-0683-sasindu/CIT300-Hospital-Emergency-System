package hospital.structures;

import hospital.model.Patient;

public class EmergencyQueue {

    private static class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            // queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println(patient.getName() + " added to the emergency queue.");
    }

    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty - no patient to treat.");
            return null;
        }
        Patient treated = front.patient;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return treated;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("   No patients currently waiting.");
            return;
        }
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println("   " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
