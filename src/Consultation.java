import java.awt.image.BufferedImage;
import java.util.Date;

public class Consultation extends Patient {

    public Consultation(){

    }

    public byte[] getEncryptedImageArray() {
        return encryptedImageArray;
    }

    public void setEncryptedImageArray(byte[] encryptedImageArray) {
        this.encryptedImageArray = encryptedImageArray;
    }

    byte[] encryptedImageArray;

    private BufferedImage noteImages;
    private Doctor doctor;
    private Date ConsulationDateandTime;
    private double cost;
    private String notes;

    public BufferedImage getNoteImages() {
        return noteImages;
    }

    public void setNoteImages(BufferedImage noteImages) {
        this.noteImages = noteImages;
    }

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
