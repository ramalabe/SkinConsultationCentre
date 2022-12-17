import java.io.Serial;
import java.io.Serializable;

public class Doctor extends Person implements Comparable<Doctor>, Serializable {

    private int medicalLicenceNumber;
    private String specialisation;

    @Serial
    private static final long serialVersionUID=1L;


    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(int medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }


    @Override
    public int compareTo(Doctor o) {
        int result = this.getSurname().compareTo(o.getSurname());
        return Integer.compare(result, 0);
    }
}
