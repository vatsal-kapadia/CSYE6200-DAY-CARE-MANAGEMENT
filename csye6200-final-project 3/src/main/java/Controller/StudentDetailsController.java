
package Controller;

import Utils.Icon;
import models.AbstractIndividual;
import models.Daycare;
import View.UpdateStudentProfile;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StudentDetailsController {

    private JPanel container;
    private Daycare daycare;
    private AbstractIndividual student;
    private StudentMainController prevController;
    
    private UpdateStudentProfile panel;
    
    public JButton backButton;
    public JButton studentPictureButton;
    
    private Map<JButton, String> iconButtonMapping = new HashMap<>();

    public StudentDetailsController(JPanel container, AbstractIndividual student, Daycare daycare, StudentMainController prevController) {
        this.container = container;
        this.daycare = daycare;
        this.student = student;
        
        this.prevController = prevController;
        
        panel = new UpdateStudentProfile(container, student, daycare);
        
        this.backButton = panel.backButton;
        this.studentPictureButton = panel.photoButton;
        
        backButton.addActionListener(l -> {
            goBackToPreviousScreen();
        });
        
        
        
        
    }
    
    public UpdateStudentProfile fetchStudentDetailsPanel() {
        setIcons();
        return panel;
    }
    
    private void setIcons() {
        Icon image = new Icon();

        iconButtonMapping.put(studentPictureButton, "/icons/children.png");
        

        for (Map.Entry<JButton, String> button : iconButtonMapping.entrySet()) {
            image.setIcon(button.getValue(), button.getKey());
        }
    }
    
    private void goBackToPreviousScreen() {
        container.remove(panel);
         CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        
        prevController.populateTable();
    }
    
    
    
}
