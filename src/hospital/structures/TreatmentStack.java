package hospital.structures;

import hospital.model.TreatmentRecord;

public class TreatmentStack {

    private static class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
        }
    }

    private Node top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("The treatment history stack is empty - nothing to remove.");
            return null;
        }
        TreatmentRecord popped = top.record;
        top = top.next;
        size--;
        return popped;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("   No treatment records yet.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
