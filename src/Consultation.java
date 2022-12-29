import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Consultation extends Patient implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;

    static ArrayList<Consultation> consultations = new ArrayList<>();

    public byte[] getEncryptedImageArray() {
        return encryptedImageArray;
    }

    public void setEncryptedImageArray(byte[] encryptedImageArray) {
        this.encryptedImageArray = encryptedImageArray;
    }

    byte[] encryptedImageArray;

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
