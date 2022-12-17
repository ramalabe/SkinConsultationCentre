import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.EventObject;

public class GUI extends WestminsterSkinConsultationManager implements ActionListener {

    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton checkDoctors = new JButton("View the List of Doctors");
        JButton sortButton = new JButton("Sort the Doctor List Alphabetically");
//        JButton consultationButton = new JButton("Add a Consultation");

//        consultationButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addConsultation();
//            }
//        });


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
//        panel.add(consultationButton);

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


//        JFrame newF1 = new JFrame("Doctor List");
//        //newF1.setBounds(100,100,1000,500);
//        JButton addConsultation = new JButton("Add Consultation");
//
//        addConsultation.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(table.getSelectedRow());
//            }
//        });
//
//        addConsultation.setSize(200,30);
//        addConsultation.setLocation(700,600);
//        //newF1.add(table);
//        table.setRowSelectionAllowed(true);
//        table.setCellEditor(new DefaultCellEditor(new JTextField()) {
//            @Override
//            public boolean isCellEditable(EventObject e) {
//                return false;
//            }
//        });
//
//        table.setRowSelectionAllowed(true);
//        //c.setSize(110,40);
//        //newF1.add(p2);
//        //p2.setSize(100,30);
//        //p2.add(addConsultation);
//        newF1.add(addConsultation);
//        newF1.add(scrollPane, BorderLayout.CENTER);
//        newF1.setSize(1000, 720);
//        newF1.setVisible(true);
//

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
        JButton addConsultation2 = new JButton("Add Consultation");

        addConsultation2.setSize(200,30);
        addConsultation2.setLocation(700,600);

        table.getSelectedColumn();

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame f2 = new JFrame("Sorted Doctor List");

        f2.add(addConsultation2);
        f2.add(scrollPane, BorderLayout.CENTER);
        f2.setSize(1000, 720);
        f2.setVisible(true);
    }

//    public void addConsultation(){
//
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
//        b1.setBackground(Color.GREEN);
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
//        p3.setSize(300,200);
//        f3.add(p3);
//        p3.add(b1,BorderLayout.WEST);
//        f3.add(l1);
//        //f3.add(b1);
//        f3.add(doctorDropdown);
//        f3.setVisible(true);
//
//
//    }

//    public void addConsultation2(){
//
//        for(int i=0;i<doctorList.size();i++){
//
//            doctorList.get(i).getName().equalsIgnoreCase(addConsultation())
//
//        }


//  }



}

