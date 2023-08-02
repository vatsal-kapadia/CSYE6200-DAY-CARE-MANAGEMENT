
package Controller;

import Utils.Icon;
import models.AbstractIndividual;
import models.Classroom;
import models.Daycare;
import models.Student;
import models.Instructor;
import View.StudentRecords;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class StudentMainController {

    private JPanel container;
    private Daycare daycare;
    public JButton backButton;   
    private StudentRecords panel;

    public JButton addStudentButton;
    
    public JButton deleteButton1;
    public JButton studentRegistrationButton;
    public JButton viewStudentButton;
    private JTable allStudentsTable;
    
    

    private Map<JButton, String> iconButtonMapping = new HashMap<>();

    public StudentMainController(JPanel container, Daycare daycare) {
        this.container = container;
        this.daycare = daycare;

        this.panel = new StudentRecords();
        
        this.addStudentButton = panel.addStudentButton;
        this.backButton = panel.backButton;
        this.deleteButton1 = panel.deleteButton1;

        this.studentRegistrationButton = panel.registrationButton;
        this.viewStudentButton = panel.viewStudentButton;

        this.allStudentsTable = panel.studentsTable;

        backButton.addActionListener(l -> {
            goBackToPreviousScreen();
        });

        viewStudentButton.addActionListener(l -> {
            fetchStudentInformation();
        });

        studentRegistrationButton.addActionListener(l -> {
            fetchStudentEnrollmentInformation();
        });

        deleteButton1.addActionListener(l -> {
            removeStudents();
        });

        addStudentButton.addActionListener(l -> {
            addNewStudent();
        });

    }

    public JPanel getStudentPanel() {
        setIcons();
        populateTable();
        return panel;
    }

    private void setIcons() {
        Icon imageHelper = new Icon();

        iconButtonMapping.put(studentRegistrationButton, "/icons/renewal.png");
        iconButtonMapping.put(addStudentButton, "/icons/add-user.png");
        iconButtonMapping.put(backButton, "/icons/previous.png");
        iconButtonMapping.put(deleteButton1, "/icons/delete.png");
        iconButtonMapping.put(viewStudentButton, "/icons/update.png");

        for (Map.Entry<JButton, String> button : iconButtonMapping.entrySet()) {
            imageHelper.setIcon(button.getValue(), button.getKey());
        }
    }

    public void populateTable() {
        allStudentsTable.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel) allStudentsTable.getModel();
        model.setRowCount(0);

        for (AbstractIndividual student : daycare.getListStudent()) {
            Object[] row = new Object[12];
            Student s = (Student) student;
            row[0] = s.getInstructorId();
            row[1] = s.getInstructorAge();
            row[2] = s.getInstructorFirstName();
            row[3] = s.getInstructorLastName();
            row[4] = s.getStudentFatherName();
            row[5] = s.getStudentMotherName();
            row[6] = s.getStudentAddress();
            row[7] = s.getParentContactNumber();
            row[8] = String.valueOf(s.isIsRenewRequired());
            row[9] = s.getLastRegisteredData();
            row[10] = s.getExpectedRenewDate();
            row[11] = s.getTeacherId();
            model.addRow(row);
        }
    }

    private void goBackToPreviousScreen() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        
        layout.previous(container);
        
    }

    private void fetchStudentInformation() {
        int selectedRow = allStudentsTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        int studentId = (int) allStudentsTable.getValueAt(selectedRow, 0);
        AbstractIndividual selectedStudent = daycare.getStudentObjById(studentId);
        if (selectedStudent != null) {
            StudentDetailsController sdc = new StudentDetailsController(container, selectedStudent, daycare, this);
            CardLayout layout = (CardLayout) container.getLayout();
            container.add(sdc.fetchStudentDetailsPanel());
            layout.next(container);
        }
    }

    private void fetchStudentEnrollmentInformation() {
        RenewalTrackingController rsc = new RenewalTrackingController(container, daycare, this);
       
        CardLayout layout = (CardLayout) container.getLayout();
         container.add(rsc.getRenewalTrackingPanel());
        layout.next(container);
    }


    private void removeStudents() {
        int selectedRow = allStudentsTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        int studentId = (int) allStudentsTable.getValueAt(selectedRow, 0);
        int teacherId = (int) allStudentsTable.getValueAt(selectedRow, 11);

        try {
            daycare.deleteStudentObjById(studentId);

            for (Classroom room : daycare.getListClassroom()) {
                for (Instructor t : room.getListTeacher()) {
                    if (t.getInstructorId() == teacherId) {
                        t.deleteStudentById(studentId);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        populateTable();
        JOptionPane.showMessageDialog(panel,
                "Student deleted successfully.");
    }

    private void addNewStudent() {
        NewStudentController asc = new NewStudentController(container, daycare, this);
       
        CardLayout layout = (CardLayout) container.getLayout();
         container.add(asc.fetchStudentPanel());
        layout.next(container);
    }
}
