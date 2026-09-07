package hospital.structures;

import hospital.model.Visit;

public class VisitLinkedList {

    private static class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public VisitLinkedList() {
        head = null;
        size = 0;
    }
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }
    
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history recorded.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("   " + current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
