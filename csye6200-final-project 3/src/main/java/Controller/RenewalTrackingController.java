/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import javax.swing.JPanel;
import models.Daycare;
import View.*;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import models.Student;
import java.time.LocalDate;
import models.AbstractIndividual;
import javax.swing.*;

/**
 *
 * @author unnatiaggarwal
 */
public class RenewalTrackingController {
    
    JPanel container;
    private Daycare daycare;
    private StudentMainController prevController;
    public javax.swing.JButton backButton;
    public javax.swing.JButton renewButton;
    private JTable RenewalTable;
    private JTable NoRenewalTable;
    

    private RenewalTracking panel;
    public RenewalTrackingController(JPanel container, Daycare daycare, StudentMainController prevController){
         this.container = container;
        this.daycare = daycare;
        this.prevController = prevController;
        
        this.panel = new RenewalTracking();
        this.backButton = panel.backButton;
        this.RenewalTable = panel.RenewalTable;
        this.NoRenewalTable = panel.NoRenewalTable;
        this.renewButton = panel.RenewButton;
        
        
        backButton.addActionListener(l -> {
            goBackHandler();
        });
        
        renewButton.addActionListener(l -> {
            changeEnrollmentDate();
        });
        
    }
    
    
    public RenewalTracking getRenewalTrackingPanel() {
        addToTable();
        return panel;
    }
    
    private void changeEnrollmentDate(){
        
       int selectedRow = RenewalTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        int studentId = (int) RenewalTable.getValueAt(selectedRow, 0);
        AbstractIndividual selectedStudent = daycare.getStudentObjById(studentId);
        
        if (selectedStudent != null) {
            Student s = (Student) selectedStudent;
            s.setLastRegisteredData(LocalDate.now());
            s.setIsRenewRequired(false);
            s.setExpectedRenewDate(s.getLastRegisteredData().plusYears(1L));
      
            JOptionPane.showMessageDialog(panel,
                    "Student enrollment renewed successfully ",
                    "Success",
                    JOptionPane.OK_OPTION);
            
           
          
            addToTable();
            
            
            
        }
    }
    
    private void addToTable(){
        RenewalTable.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel) RenewalTable.getModel();
        model.setRowCount(0);
        
        
        for (Student student : daycare.getStudentListwithIsNeedRenewTrue()) {
            System.out.println(student.getInstructorId());
            Object[] row = new Object[5];
            row[0] = student.getInstructorId();
            row[1] = student.getInstructorFirstName()+ " " + student.getInstructorLastName();
            row[2] = student.getInstructorAge();
            row[3] = student.getLastRegisteredData().toString();
            row[4] = student.getExpectedRenewDate().toString();
            model.addRow(row);
        }
        
       NoRenewalTable.setAutoCreateRowSorter(true);
        DefaultTableModel m = (DefaultTableModel) NoRenewalTable.getModel();
        m.setRowCount(0);
        

       for (Student student : daycare.getStudentListwithIsNeedRenewFalse()) {
            Object[] row = new Object[5];
            row[0] = student.getInstructorId();
            row[1] = student.getInstructorFirstName()+ " " + student.getInstructorLastName();
            row[2] = student.getInstructorAge();
            row[3] = student.getLastRegisteredData().toString();
            row[4] = student.getExpectedRenewDate().toString();
            m.addRow(row);
        }
    }
    
    private void goBackHandler() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        prevController.populateTable();
    }
     
}
