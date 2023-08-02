package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vaccination {

    private List<LocalDate> doseDates = new ArrayList<>();
    private String vaxName;
    private LocalDate nextDoseDate;
    private LocalDate lastDoseDate;
    private int studentId;
    private int numberOfDoses;

    public Vaccination(String csv) {

        String[] item = csv.split(",");

        try {
            this.studentId = Integer.parseInt(item[0]);
            this.vaxName = item[1];
            this.numberOfDoses = Integer.parseInt(item[2]);
            this.lastDoseDate = LocalDate.parse(item[3]);
            this.nextDoseDate = LocalDate.parse(item[4]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Unable to parse vaccine info");
        }

        for (int i = 3; i < item.length; i++) {
            doseDates.add(LocalDate.parse(item[i]));
        }

    }

    public String getVaxName() {
        return vaxName;
    }

    public void setVaxName(String vaxName) {
        this.vaxName = vaxName;
    }

    public List<LocalDate> getDoseDates() {
        return doseDates;
    }

    public void setDoseDates(List<LocalDate> doseDates) {
        this.doseDates = doseDates;
    }

    public LocalDate getLastDoseDate() {
        return lastDoseDate;
    }

    public void setLastDoseDate(LocalDate lastDoseDate) {
        this.lastDoseDate = lastDoseDate;
    }

    public LocalDate getNextDoseDate() {
        return nextDoseDate;
    }

    public void setNextDoseDate(LocalDate nextDoseDate) {
        this.nextDoseDate = nextDoseDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNumberOfDoses() {
        return numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        this.numberOfDoses = numberOfDoses;
    }
    
    

    @Override
    public String toString() {
        return "Vax{" + vaxName
                + "DoesDate=" + doseDates
                + '}';
    }
}
