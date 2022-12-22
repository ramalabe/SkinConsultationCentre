import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GUI extends WestminsterSkinConsultationManager implements ActionListener {

    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton checkDoctors = new JButton("View the List of Doctors");
        JButton sortButton = new JButton("Sort the Doctor List Alphabetically");
        JButton consultationButton = new JButton("Add a Consultation");
        JButton showConsultation = new JButton("Show Consultations");

        consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addConsultation();
            }
        });


        showConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //showConsultations();
            }
        });


        checkDoctors.addActionListener(this);

        sortButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sortedDoctorList();
            }
        });

        checkDoctors.setBackground(Color.YELLOW);


        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(checkDoctors);
        panel.add(sortButton);
        panel.add(consultationButton);

        frame.add(panel, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Skin Consultation Center System");
        frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doctorListWindow();
    }

    public void doctorListWindow(){


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Date of Birth");
        model.addColumn("Mobile Number");
        model.addColumn("Specialisation");
        model.addColumn("Medical License Number");


        for (Doctor doctor : doctorList) {
            model.addRow(new Object[]{doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getSpecialisation(), doctor.getMedicalLicenceNumber()});
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);


        JFrame newF1 = new JFrame("Doctor List");
        //newF1.add(table);

        table.setEnabled(false);
        newF1.add(scrollPane, BorderLayout.CENTER);
        newF1.setSize(1000, 720);
        newF1.setVisible(true);


    }


    public void sortedDoctorList(){

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Date of Birth");
        model.addColumn("Mobile Number");
        model.addColumn("Specialisation");
        model.addColumn("Medical License Number");
        Collections.sort(doctorList);
        for (Doctor doctor : doctorList) {
            model.addRow(new Object[]{doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getSpecialisation(), doctor.getMedicalLicenceNumber()});
        }

        JTable table = new JTable(model);

        table.getSelectedColumn();

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame f2 = new JFrame("Sorted Doctor List");
        table.setEnabled(false);
        f2.add(scrollPane, BorderLayout.CENTER);
        f2.setSize(1000, 720);
        f2.setVisible(true);
    }

    public void addConsultation(){

        ArrayList<Consultation> consultations = new ArrayList<>();

        // create the dropdown menu
        JComboBox<String> dropdown = new JComboBox<>();

        for (Doctor doctor : doctorList) {
            dropdown.addItem(doctor.getName()+" "+doctor.getSurname());
        }

// create the button
        JButton button = new JButton("Consult");

// create the label
        JLabel label = new JLabel("Please select a Doctor: ");

// create a panel to hold the components
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(dropdown);
        panel.add(button);

// create the frame and add the panel
        JFrame frame = new JFrame("Add Consultation");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame j1 = new JFrame("Enter Appointment Details");
                JPanel p1 = new JPanel();

                //System.out.println(dropdown.getSelectedItem().toString());
                //System.out.println(dropdown.getSelectedItem().toString().contains("Ramal"));

                //String selectedDoctor = (Objects.requireNonNull(Objects.requireNonNull(dropdown.getSelectedItem()).getClass().getName()));

                // Create a Date object for the current date and time
                Date now = new Date();

                // Create a SpinnerDateModel with the Date object as the value
                SpinnerDateModel dateModel = new SpinnerDateModel(now, null, null, java.util.Calendar.MINUTE);

                // Create a JSpinner with the SpinnerDateModel
                JSpinner spinner = new JSpinner(dateModel);

                // Set the format of the spinner's editor to display the date and time
                JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
                spinner.setEditor(editor);
                Date selectedDate = (Date) spinner.getValue();


                // Create a SpinnerNumberModel with an integer value
                SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, 10000000, 1);

                        // Create a JSpinner with the SpinnerNumberModel
                JSpinner costSpinner = new JSpinner(numberModel);

                int selectedValue = (int) costSpinner.getValue();

                JTextField noteField = new JTextField();

                String notes = noteField.getText();

//                for (Doctor doctor : doctorList) {
//                    if (Objects.requireNonNull(dropdown.getSelectedItem()).toString().contains(doctor.getName())) {
//                        Consultation c1 = new Consultation(doctor,selectedDate,selectedValue,notes);
//                    }
//                }

                JLabel l1 = new JLabel("Select the Date & Time: ");
                JLabel l2 = new JLabel("Select the Cost (£): ");
                JLabel l3 = new JLabel("Notes: ");
                JButton b1 = new JButton("Submit");
                JLabel l4 = new JLabel("Patients Name: ");
                JTextField nameField = new JTextField();
                JLabel l5 = new JLabel("Patients Surname: ");
                JTextField surnameField = new JTextField();
                JLabel l6 = new JLabel("Patients Date of Birth: ");
                JTextField dobField = new JTextField();
                JLabel l7 = new JLabel("Patients Mobile Number: ");
                JTextField numberField = new JTextField();
                JLabel l8 = new JLabel("Patient Id: ");
                JTextField patientidField = new JTextField();


                ((AbstractDocument) numberField.getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        // Only allow integer input
                        if (string.matches("^\\d+$")) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        // Only allow integer input
                        if (text.matches("^\\d+$")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });


                noteField.setPreferredSize(new Dimension(200,50));
                j1.setPreferredSize(new Dimension(2000,1500));
                p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));


                p1.add(l1);
                p1.add(spinner);
                p1.add(l2);
                p1.add(costSpinner);
                p1.add(l3);
                p1.add(noteField);
                j1.add(p1);
                p1.add(l4);
                p1.add(nameField);
                p1.add(l5);
                p1.add(surnameField);
                p1.add(l6);
                p1.add(dobField);
                p1.add(l7);
                p1.add(numberField);
                p1.add(l8);
                p1.add(patientidField);

                p1.add(b1);
                j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                j1.pack();
                j1.setVisible(true);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        for (int i = 0; i < doctorList.size(); i++) {
                            if (Objects.requireNonNull(dropdown.getSelectedItem()).toString().contains(doctorList.get(i).getName())) {
                                if (consultations.isEmpty()) {
                                    Consultation c1 = new Consultation(doctorList.get(i), selectedDate, selectedValue, notes);
                                    c1.setName(nameField.getText());
                                    c1.setSurname(surnameField.getText());
                                    c1.setDateOfBirth(dobField.getText());
                                    c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                    c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                    consultations.add(c1);
                                    JOptionPane.showMessageDialog(null, "Consultation Added with Doctor " + doctorList.get(i).getName(), "Success", JOptionPane.INFORMATION_MESSAGE);


                                } else if (!(consultations.get(i).getConsulationDateandTime().equals(selectedDate))) {

                                    Consultation c1 = new Consultation(doctorList.get(i), selectedDate, selectedValue, notes);
                                    c1.setName(nameField.getText());
                                    c1.setSurname(surnameField.getText());
                                    c1.setDateOfBirth(dobField.getText());
                                    c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                    c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                    consultations.add(c1);
                                    JOptionPane.showMessageDialog(null, "Consultation Added with Doctor " + doctorList.get(i).getName(), "Success", JOptionPane.INFORMATION_MESSAGE);


                                }

                            } else {

                                Random rng = new Random();
                                ArrayList<Doctor> excludedDoctorList = (ArrayList<Doctor>) doctorList.clone();
                                excludedDoctorList.remove(i);
                                if (excludedDoctorList.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Doctor " + doctorList.get(i).getName() + " isn't available at the mentioned time, unable to find any other Doctors at this time", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                    continue;
                                } else {
                                    int index = rng.nextInt(excludedDoctorList.size());
                                    Doctor newDoctor = excludedDoctorList.get(index);
                                    JOptionPane.showMessageDialog(null, "Doctor" + doctorList.get(i).getName() + "is not available at that time, consultation will be scheduled with Doctor " + newDoctor.getName(), "Alert", JOptionPane.INFORMATION_MESSAGE);
                                    Consultation c1 = new Consultation(newDoctor, selectedDate, selectedValue, notes);
                                    c1.setName(nameField.getText());
                                    c1.setSurname(surnameField.getText());
                                    c1.setDateOfBirth(dobField.getText());
                                    c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                    c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                    consultations.add(c1);
                                }
                            }
                        }
                    }
                });


                showConsultation.addAc



            }
        });
    };

    public void showConsultations(){


    }



}