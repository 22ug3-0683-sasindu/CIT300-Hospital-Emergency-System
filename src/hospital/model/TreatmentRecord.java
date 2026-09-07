package hospital.model;

public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentDescription;
    private String completionTime;

    public TreatmentRecord(int patientId, String patientName, String treatmentDescription, String completionTime) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDescription = treatmentDescription;
        this.completionTime = completionTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Treatment: " + treatmentDescription +
                " | Completed at: " + completionTime;
    }
}
