import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.*;

public class GUI extends WestminsterSkinConsultationManager implements ActionListener {

    ArrayList<Consultation> consultations = new ArrayList<>();

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
                showConsultations();
            }
        });


        checkDoctors.addActionListener(this);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortedDoctorList();
            }
        });



        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(checkDoctors);
        panel.add(sortButton);
        panel.add(consultationButton);
        panel.add(showConsultation);

        frame.add(panel, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Skin Consultation Center System");
        frame.setSize(1000, 1000);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doctorListWindow();
    }

    public void doctorListWindow() {


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

        table.setEnabled(false);
        newF1.add(scrollPane, BorderLayout.CENTER);
        newF1.setSize(1000, 720);
        newF1.setVisible(true);


    }


    public void sortedDoctorList() {

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

    public void addConsultation() {

        // create the dropdown menu
        JComboBox<String> dropdown = new JComboBox<>();

        for (Doctor doctor : doctorList) {
            dropdown.addItem(doctor.getName() + " " + doctor.getSurname());
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
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform any necessary cleanup or other actions before the frame is closed
                // Close the frame
                frame.dispose();
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame j1 = new JFrame("Enter Appointment Details");
                JPanel p1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));

                Date initialDate = new Date();
                Date minimumDate = new Date(0);
                Date maximumDate = new Date(Long.MAX_VALUE);
                SpinnerDateModel dateModel = new SpinnerDateModel(initialDate, minimumDate, maximumDate, Calendar.MINUTE);

                // Create a JSpinner with the SpinnerDateModel
                JSpinner spinner = new JSpinner(dateModel);

                JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy HH:mm");
                spinner.setEditor(editor);

                // Create a SpinnerNumberModel
                SpinnerNumberModel numberModel = new SpinnerNumberModel(0.0, 0.0, null, 0.1);

                // Create a JSpinner with the SpinnerNumberModel
                JSpinner costSpinner = new JSpinner(numberModel);

                JTextField noteField = new JTextField();


                JLabel l1 = new JLabel("Select the Date & Time: ");
                JLabel l2 = new JLabel("Select the Cost (£): ");
                JLabel l3 = new JLabel("Notes: ");
                JButton b1 = new JButton("Submit");
                b1.setEnabled(false);
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

                ((AbstractDocument) patientidField.getDocument()).setDocumentFilter(new DocumentFilter() {
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


                j1.setPreferredSize(new Dimension(1200, 700));
                p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

                JButton uploadButton = new JButton("Upload Images");
                uploadButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int returnVal = fileChooser.showOpenDialog(frame);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            // do something with the selected file (e.g. display the image, upload it to a server, etc.)
                        }
                    }
                });



                p1.add(l1);
                p1.add(spinner);
                p1.add(l2);
                p1.add(costSpinner);
                p1.add(l3);
                p1.add(noteField);
                p1.add(uploadButton, BorderLayout.EAST);
                p1.add(new JSeparator(SwingConstants.HORIZONTAL));
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
                j1.pack();
                j1.setVisible(true);

                j1.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // Perform any necessary cleanup or other actions before the frame is closed
                        // Close the frame
                        j1.dispose();
                    }
                });


                DocumentListener listener = new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        checkFields();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        checkFields();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        // Not used
                    }

                    private void checkFields() {
                        if (!nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !dobField.getText().isEmpty() && !numberField.getText().isEmpty() && !patientidField.getText().isEmpty()) {
                            b1.setEnabled(true);
                        } else {
                            b1.setEnabled(false);
                        }
                    }
                };

                // Attach the DocumentListener to the text fields
                nameField.getDocument().addDocumentListener(listener);
                surnameField.getDocument().addDocumentListener(listener);
                dobField.getDocument().addDocumentListener(listener);
                numberField.getDocument().addDocumentListener(listener);
                patientidField.getDocument().addDocumentListener(listener);


                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date selectedDate = (Date) spinner.getValue();
                        double selectedValue = ((Number) costSpinner.getValue()).doubleValue();
                        String notes = noteField.getText();


                        for (int i = 0; i < doctorList.size(); i++) {
                            if (Objects.requireNonNull(dropdown.getSelectedItem()).toString().contains(doctorList.get(i).getName())) {
                                if ( consultations.isEmpty()) {
                                    Consultation c1 = new Consultation();
                                    c1.setCost(selectedValue);
                                    c1.setNotes(notes);
                                    c1.setDoctor(doctorList.get(i));
                                    c1.setName(nameField.getText());
                                    c1.setSurname(surnameField.getText());
                                    c1.setDateOfBirth(dobField.getText());
                                    c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                    c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                    c1.setConsulationDateandTime(selectedDate);
                                    consultations.add(c1);
                                    JOptionPane.showMessageDialog(null, "Consultation Added with Doctor " + doctorList.get(i).getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                                    j1.dispose();

                                }

                                else if (!(consultations.get(i).getConsulationDateandTime().equals(selectedDate))){
                                    Consultation c1 = new Consultation();
                                    c1.setCost(selectedValue);
                                    c1.setNotes(notes);
                                    c1.setDoctor(doctorList.get(i));
                                    c1.setName(nameField.getText());
                                    c1.setSurname(surnameField.getText());
                                    c1.setDateOfBirth(dobField.getText());
                                    c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                    c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                    c1.setConsulationDateandTime(selectedDate);
                                    consultations.add(c1);
                                    JOptionPane.showMessageDialog(null, "Consultation Added with Doctor " + doctorList.get(i).getName(), "Success", JOptionPane.INFORMATION_MESSAGE);
                                    j1.dispose();
                                }

                                else {

                                    Random rng = new Random();
                                    ArrayList<Doctor> excludedDoctorList = (ArrayList<Doctor>) doctorList.clone();
                                    excludedDoctorList.remove(i);
                                    if (excludedDoctorList.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Doctor " + doctorList.get(i).getName() + " isn't available at the mentioned time, unable to find any other Doctors at this time", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                        j1.dispose();
                                    } else {
                                        int index = rng.nextInt(excludedDoctorList.size());
                                        Doctor newDoctor = excludedDoctorList.get(index);
                                        JOptionPane.showMessageDialog(null, "Doctor " + doctorList.get(i).getName() + " is not available at that time, consultation will be scheduled with Doctor " + newDoctor.getName(), "Alert", JOptionPane.INFORMATION_MESSAGE);
                                        Consultation c1 = new Consultation();
                                        c1.setCost(selectedValue);
                                        c1.setNotes(notes);
                                        c1.setDoctor(newDoctor);
                                        c1.setName(nameField.getText());
                                        c1.setSurname(surnameField.getText());
                                        c1.setDateOfBirth(dobField.getText());
                                        c1.setMobileNumber(Integer.parseInt(numberField.getText()));
                                        c1.setPatientId(Integer.parseInt(patientidField.getText()));
                                        c1.setConsulationDateandTime(selectedDate);
                                        consultations.add(c1);
                                        j1.dispose();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
    }
    private int p=0;
    public void showConsultations() {

        JFrame f = new JFrame("Consultations");
        JPanel p = new JPanel();

        for (int x=0;x<consultations.size();x++){

            JButton b1 = new JButton(consultations.get(x).getName()+" - "+consultations.get(x).getConsulationDateandTime());

            int finalX = x;
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame f1 = new JFrame("Details of "+consultations.get(finalX).getName()+"'s Appointment");
                    JPanel p1 = new JPanel();

                    JLabel l3 = new JLabel("Doctor's Name: ");
                    JTextArea tArea0 = new JTextArea(consultations.get(finalX).getDoctor().getName()+" "+consultations.get(finalX).getDoctor().getSurname());
                    tArea0.setEditable(false);
                    JLabel l4 = new JLabel("Patient's Name: ");
                    JTextArea tArea1 = new JTextArea(consultations.get(finalX).getName());
                    tArea1.setEditable(false);
                    JLabel l5 = new JLabel("Patient's Surname: ");
                    JTextArea tArea2 = new JTextArea(consultations.get(finalX).getSurname());
                    tArea2.setEditable(false);
                    JLabel l6 = new JLabel("Patient's Date of Birth: ");
                    JTextArea tArea3 = new JTextArea(consultations.get(finalX).getDateOfBirth());
                    tArea3.setEditable(false);
                    JLabel l7 = new JLabel("Patient's Mobile Number: ");
                    JTextArea tArea4 = new JTextArea(String.valueOf(consultations.get(finalX).getMobileNumber()));
                    tArea4.setEditable(false);
                    JLabel l8 = new JLabel("Patient Id: ");
                    JTextArea tArea5 = new JTextArea(String.valueOf(consultations.get(finalX).getPatientId()));
                    tArea5.setEditable(false);
                    JLabel l9 = new JLabel("Scheduled Date and Time: ");
                    JTextArea tArea6 = new JTextArea(String.valueOf(consultations.get(finalX).getConsulationDateandTime()));
                    tArea6.setEditable(false);
                    JLabel l10 = new JLabel("Appointment Cost: ");
                    JTextArea tArea7 = new JTextArea(String.valueOf(consultations.get(finalX).getCost()));
                    tArea7.setEditable(false);
                    JLabel l11 = new JLabel("Appointment Notes: ");
                    JTextArea tArea8 = new JTextArea(consultations.get(finalX).getNotes());
                    tArea8.setEditable(false);



                    p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
                    f1.add(p1);
                    p1.add(l3);
                    p1.add(tArea0);
                    p1.add(l4);
                    p1.add(tArea1);
                    p1.add(l5);
                    p1.add(tArea2);
                    p1.add(l6);
                    p1.add(tArea3);
                    p1.add(l7);
                    p1.add(tArea4);
                    p1.add(l8);
                    p1.add(tArea5);
                    p1.add(l9);
                    p1.add(tArea6);
                    p1.add(l10);
                    p1.add(tArea7);
                    p1.add(l11);
                    p1.add(tArea8);

                    f1.pack();
                    f1.setVisible(true);
                }



                });


            p.add(b1);
            }
            f.setPreferredSize(new Dimension(600, 400));
            f.add(p);
            f.pack();
            f.setVisible(true);
        }

}