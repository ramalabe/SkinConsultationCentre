import java.util.ArrayList;

interface SkinConsultationManager{

    ArrayList <Doctor> doctorList = new ArrayList<>();

    void addNewDoctor();
    void deleteDoctor();
    void printDoctors();
    void saveFile();

}