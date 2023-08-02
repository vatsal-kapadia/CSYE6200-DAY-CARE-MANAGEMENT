package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Student extends AbstractIndividual {
    private int studentAge;
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentFatherName;
    private LocalDate lastRegisteredData;
    private String studentMotherName;
    private int teacherId;
    private boolean isRenewRequired;
    private String parentContactNumber;
    private String ageGroupAlotted;
    private String studentAddress;
    private LocalDate expectedRenewDate;
    private int classroomId;
    private String parentEmailAddress;
    private String parentName;
    
    private List<Vaccination> VaxList = new ArrayList<>();


    public Student(String csv) {
        
        //new student
        String[] item = csv.split(",");
        this.studentId = Integer.parseInt(item[0]);
        this.studentAge = Integer.parseInt(item[1]);
        this.studentFirstName = item[2];
        this.studentLastName = item[3];
        this.lastRegisteredData = LocalDate.parse(item[4]);
        this.expectedRenewDate = lastRegisteredData.plusYears(1L);
        this.studentFatherName = item[5];
        this.studentMotherName = item[6];
        this.studentAddress = item[7];
        this.parentContactNumber = item[8];
        this.isRenewRequired = Boolean.parseBoolean(item[9]);
        this.ageGroupAlotted = item[10];

    }

    public Student() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getInstructorId() {
        return this.studentId;
    }

    @Override
    public void setInstructorId(int id) {
        this.studentId = id;
    }

    @Override
    public void setInstructorAge(int age) {
        this.studentAge = age;
    }

    @Override
    public int getInstructorAge() {
        return this.studentAge;
    }


    public void setClassroomId(int cid) {
        this.classroomId = cid;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;

    }
    
    @Override
    public String getInstructorFirstName() {
        return this.studentFirstName;
    }

    @Override
    public String getInstructorLastName() {
        return this.studentLastName;
    }

    public void setTid(int tid) {
        this.teacherId = tid;
    }

    public void addVax(Vaccination v) {
        VaxList.add(v);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public LocalDate getLastRegisteredData() {
        return lastRegisteredData;
    }

    public void setLastRegisteredData(LocalDate lastRegisteredData) {
        this.lastRegisteredData = lastRegisteredData;
    }

    public LocalDate getExpectedRenewDate() {
        return expectedRenewDate;
    }

    public void setExpectedRenewDate(LocalDate expectedRenewDate) {
        this.expectedRenewDate = expectedRenewDate;
    }

    public boolean isIsRenewRequired() {
        return isRenewRequired;
    }

    public void setIsRenewRequired(boolean isRenewRequired) {
        this.isRenewRequired = isRenewRequired;
    }

    public void setStudentLastName(String LastName) {
        this.studentLastName = LastName;
    }
    
    
    public String getStudentFatherName() {
        return studentFatherName;
    }

    public String getStudentMotherName() {
        return studentMotherName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getParentContactNumber() {
        return parentContactNumber;
    }

    public void setParentContactNumber(String parentContactNumber) {
        this.parentContactNumber = parentContactNumber;
    }
    

    public List <String> getVaxCount() {
        // Initiate a list of 6 elements
        // ["Hib", "DTaP", "Polio", "Hepatitis B", "MMR", "Varicella"]
        List<Integer> vaxCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

        for (Vaccination v : VaxList) {
            switch (v.getVaxName()) {
                case "Hib":
                    vaxCount.set(0, vaxCount.get(0) + v.getNumberOfDoses());
                    break;
                case "DTaP":
                    vaxCount.set(1, vaxCount.get(1) + v.getNumberOfDoses());
                    break;
                case "Polio":
                    vaxCount.set(2, vaxCount.get(2) + v.getNumberOfDoses());
                    break;
                case "Hepatitis B":
                    vaxCount.set(3, vaxCount.get(3) + v.getNumberOfDoses());
                    break;
                case "MMR":
                    vaxCount.set(4, vaxCount.get(4) + v.getNumberOfDoses());
                    break;
                case "Varicella":
                    vaxCount.set(5, vaxCount.get(5) + v.getNumberOfDoses());
                    break;
            }
        }
        List <String> vaxCountString = new ArrayList<>();
        // if age less than 24 months, HIB 4 doses, DTaP 4 doses, Polio 3 doses, Hepatitis B 3 doses, MMR 1 doses, Varicella 1 doses
        // if age greater than 24 months, HIB 3 doses, DTaP 4 doses, Polio 4 doses, Hepatitis B 3 doses, MMR 1 doses, Varicella 2 doses
        if (this.studentAge < 24) {
            vaxCountString.add( String.valueOf(vaxCount.get(0)) + "/4");  // "HIB: "
            vaxCountString.add( String.valueOf(vaxCount.get(1)) + "/4");  // "DTaP: "
            vaxCountString.add( String.valueOf(vaxCount.get(2)) + "/3");  // "Polio: "
            vaxCountString.add( String.valueOf(vaxCount.get(3)) + "/3");  // "Hepatitis B:"
            vaxCountString.add( String.valueOf(vaxCount.get(4)) + "/1");  // "MMR: "
            vaxCountString.add( String.valueOf(vaxCount.get(5)) + "/1");  // "Varicella: "
        } else {
            vaxCountString.add( String.valueOf(vaxCount.get(0)) + "/4");  // "HIB: "
            vaxCountString.add( String.valueOf(vaxCount.get(1)) + "/4");  // "DTaP: "
            vaxCountString.add( String.valueOf(vaxCount.get(2)) + "/4");  // "Polio: "
            vaxCountString.add( String.valueOf(vaxCount.get(3)) + "/3");  // "Hepatitis B: "
            vaxCountString.add( String.valueOf(vaxCount.get(4)) + "/2");  // "MMR: "
            vaxCountString.add( String.valueOf(vaxCount.get(5)) + "/2");  // "Varicella: "
        }
        return vaxCountString;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentAge=" + studentAge +
                ", studentId=" + studentId +
                ", studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", studentFatherName='" + studentFatherName + '\'' +
                ", lastRegisteredData=" + lastRegisteredData +
                ", studentMotherName='" + studentMotherName + '\'' +
                ", teacherId=" + teacherId +
                ", isRenewRequired=" + isRenewRequired +
                ", parentContactNumber='" + parentContactNumber + '\'' +
                ", ageGroupAlotted='" + ageGroupAlotted + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", expectedRenewDate=" + expectedRenewDate +
                ", classroomId=" + classroomId +
                ", parentEmailAddress='" + parentEmailAddress + '\'' +
                ", parentName='" + parentName + '\'' +
                ", VaxList=" + VaxList +
                '}';
    }

    public String toCSV(){
        return studentId + "," +
                studentAge + "," +
                studentFirstName + "," +
                studentLastName + "," +
                lastRegisteredData.toString() ;
    }


}
