import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.Objects;

public class GUI extends WestminsterSkinConsultationManager implements ActionListener {

    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton checkDoctors = new JButton("View the List of Doctors");
        JButton sortButton = new JButton("Sort the Doctor List Alphabetically");
        JButton consultationButton = new JButton("Add a Consultation");

        consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addConsultation();
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

                for (Doctor doctor : doctorList) {
                    if (Objects.requireNonNull(dropdown.getSelectedItem()).toString().contains(doctor.getName())) {
                        Consultation c1 = new Consultation(doctor,selectedDate,selectedValue,notes);
                    }
                }

                JLabel l1 = new JLabel("Select the Date & Time: ");
                JLabel l2 = new JLabel("Select the Cost (£): ");
                JLabel l3 = new JLabel("Notes: ");
                JButton b1 = new JButton("Submit");

                noteField.setPreferredSize(new Dimension(200,50));
                j1.setPreferredSize(new Dimension(1000,500));
                p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));


                p1.add(l1);
                p1.add(spinner);
                p1.add(l2);
                p1.add(costSpinner);
                p1.add(l3);
                p1.add(noteField);
                j1.add(p1);
                p1.add(b1);
                j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                j1.pack();
                j1.setVisible(true);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Doctor d1 = new Doctor();

                    }
                });

            }
        });





//        JFrame f3 = new JFrame("Add Consultation");
//        JPanel p3 = new JPanel();
//
//        f3.setSize(1000,500);
//        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JLabel l1 = new JLabel("Please select a Doctor: ");
//        JComboBox<String> doctorDropdown = new JComboBox<>();
//        JButton b1 = new JButton("Add Consultation");
//
//
//        for (Doctor doctor : doctorList) {
//            doctorDropdown.addItem(doctor.getName()+" "+doctor.getSurname());
//        }
//
//        String selectedItem = (String) doctorDropdown.getSelectedItem();
//
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //addConsultation2();
//
//                for (Doctor doctor : doctorList) {
//                    doctor.getName();
//
//                    if ((selectedItem.contains(doctor.getName()))){
//
//                    }
//
//                    Doctor d1 = new Consultation();
//
//
//                }
//
//
//            }
//        });
//
//
//        b1.setLocation(600,700);
//        //p3.setSize(300,200);
//        p3.add(b1);
//        //p3.add(b1,BorderLayout.WEST);
//        p3.add(l1);
//        //f3.add(b1);
//        p3.add(doctorDropdown);
//        p3.setVisible(true);
//        f3.setVisible(true);
//
//

  }



}

