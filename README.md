# Mini Hospital Emergency Management System

## Project Overview
This project is an individual assignment for **CIT300 - Data Structures and Algorithms**[cite: 2]. It simulates a hospital emergency unit managing patient registrations, emergency queues, treatment histories, and visit records using core Java data structures[cite: 2].

---

## Data Structures Implemented

1. **Binary Search Tree (BST) - Patient Records**[cite: 2]
   - Stores patient records using `Patient ID` as the unique key[cite: 2].
   - Supports insertion, search, deletion, and in-order traversal to list patients in ascending order[cite: 2].

2. **Queue - Emergency Patient Queue**[cite: 2]
   - Manages arriving emergency patients following the FIFO (First-In, First-Out) principle[cite: 2].
   - Supports `enqueue`, `dequeue`, and displaying waiting patients with empty-queue handling[cite: 2].

3. **Stack - Treatment History**[cite: 2]
   - Records completed treatments using the LIFO (Last-In, First-Out) principle[cite: 2].
   - Supports `push`, `pop`, and displaying treatment records[cite: 2].

4. **Singly Linked List - Patient Visit History**[cite: 2]
   - Tracks individual patient medical history[cite: 2].
   - Supports adding visits, removing visits, searching visits, and displaying complete visit history[cite: 2].

---

## Project Structure
```text
hospital-emergency-system/
├── src/
│   └── hospital/
│       ├── Main.java
│       ├── model/
│       │   ├── Patient.java
│       │   ├── TreatmentRecord.java
│       │   └── Visit.java
│       └── structures/
│           ├── EmergencyQueue.java
│           ├── PatientBST.java
│           ├── TreatmentStack.java
│           └── VisitLinkedList.java
└── README.md
