import java.util.Date;

public class Consultation extends Patient {

    public Consultation(){

    }

    private Doctor doctor;
    private Date ConsulationDateandTime;
    private double cost;
    private String notes;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getConsulationDateandTime() {
        return ConsulationDateandTime;
    }

    public void setConsulationDateandTime(Date consulationDateandTime) {
        ConsulationDateandTime = consulationDateandTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
