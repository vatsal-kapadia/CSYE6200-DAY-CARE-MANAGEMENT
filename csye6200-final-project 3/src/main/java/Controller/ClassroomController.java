
package Controller;

import models.Classroom;
import models.Daycare;
import View.DaycareClassrooms;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Instructor;

public class ClassroomController {

    private JPanel container;
    private Daycare daycare;

    private DaycareClassrooms panel;

    private JButton backButton;
    private JTable classTable;

    
    private PanelInfo panel1;
    private PanelInfo panel2;
    private PanelInfo panel3;
    private PanelInfo panel4;
    private PanelInfo panel5;
    private PanelInfo panel6;
    
    public static class PanelInfo{
        private JTable infoTable;
        
        
        public PanelInfo(JTable t){
            infoTable = t;
        }
        
        public void fillClassroomIntoPanel(Classroom classRoomObj){
            DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
            model.setRowCount(0);
            Object[] row1 = new Object[2];
            Object[] row2 = new Object[2];
            Object[] row3 = new Object[2];
            Object[] row4 = new Object[2];
            row1[0] = "Max Possible Groups";
            row1[1] = classRoomObj.getClassroomNumber();
            int[] rangeArr = classRoomObj.getRangeOfAge();
            row2[0] = "Range";
            row2[1] = rangeArr[0] + " - " + rangeArr[1];
            row3[0] = "Group Size";
            row3[1] = classRoomObj.getClassroomNumber();
            row4[0] = "Total Students";
            int count = 0;
            for(Instructor teacher
                    : classRoomObj.getListTeacher()){
                count += teacher.getCount();
            }
            row4[1] = count;
            model.addRow(row1);
            model.addRow(row2);
            model.addRow(row3);
            model.addRow(row4);
        }
        
    }
    

    public ClassroomController(JPanel container, Daycare daycare) {
        this.container = container;
        this.daycare = daycare;

        this.panel = new DaycareClassrooms();

        this.backButton = panel.backButton;


        backButton.addActionListener(l -> {
            goBack();
        });



    }

    public DaycareClassrooms getClassroomsPanel() {

        displayClassrooms();
        return panel;
    }
    
    
    
    public void displayClassrooms(){
        List<Classroom> classRoomList = daycare.getListClassroom();
        panel1 = new PanelInfo(panel.jTable1);
        panel1.fillClassroomIntoPanel(classRoomList.get(0));
        
        panel2 = new PanelInfo(panel.jTable2);
        panel2.fillClassroomIntoPanel(classRoomList.get(1));
        
        panel3 = new PanelInfo(panel.jTable3);
        panel3.fillClassroomIntoPanel(classRoomList.get(2));
        
        panel4 = new PanelInfo(panel.jTable4);
        panel4.fillClassroomIntoPanel(classRoomList.get(3));
        
        panel5 = new PanelInfo(panel.jTable5);
        panel5.fillClassroomIntoPanel(classRoomList.get(4));
//        
        panel6 = new PanelInfo(panel.jTable6);
        panel6.fillClassroomIntoPanel(classRoomList.get(5));

    }

    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }

}
