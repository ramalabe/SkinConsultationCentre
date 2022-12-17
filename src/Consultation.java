import java.time.LocalDateTime;

public class Consultation extends Doctor{

    private LocalDateTime ConsulationDateandTime;
    private int cost;
    private String notes;

    public LocalDateTime getConsulationDateandTime() {
        return ConsulationDateandTime;
    }

    public void setConsulationDateandTime(LocalDateTime consulationDateandTime) {
        ConsulationDateandTime = consulationDateandTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
