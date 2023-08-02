package models;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Instructor extends AbstractIndividual {

    private int instructorId;
    private int instructorAge;
    //private String Name;
    private String instructorLastName;
    private String instructorFirstName;
    private int salary;
    private int classroomId;
    private LocalDate dateOfReview;
    private long daysForReview;
    private LocalDate nextReviewDate;
    private int lastReviewScore = 0;
    private List<Student> listStudent = new ArrayList<>();
    private int count = 0;
    private int size;

    public Instructor(String TeacherCSVString, int tId, int size, int cId){
        this.instructorId = tId;
        this.size = size;
        this.classroomId = cId;

        String[] item = TeacherCSVString.split(",");
        this.instructorFirstName = item[0];
        this.instructorLastName = item[1];
        this.instructorAge = Integer.parseInt(item[2]);
        this.dateOfReview = LocalDate.parse(item[3]);
        // Calculate Review in days based on current date
        this.salary = Integer.parseInt(item[4]);
        this.lastReviewScore = Integer.parseInt(item[5]);
        this.nextReviewDate = dateOfReview.plusDays(365);
        this.daysForReview = Duration.between(LocalDate.now().atStartOfDay(), nextReviewDate.atStartOfDay()).toDays(); // another option
        
    }


    public Instructor(int id, int size, String firstName, String lastName, int age, int wage, int cid, String reviewDate ){
        this.instructorFirstName = firstName;
        this.instructorLastName = lastName;
        this.nextReviewDate = dateOfReview.plusDays(365);
        this.instructorId = id;
        this.size = size;
        this.instructorAge = age;
        //Random r = new Random();
        this.salary = wage;
        this.classroomId = cid;
        this.daysForReview = Duration.between(LocalDate.now().atStartOfDay(), dateOfReview.atStartOfDay()).toDays();

        this.dateOfReview = LocalDate.parse(reviewDate);
        // Calculate Review in days based on current date
        
        // Duration.
//        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

        // Print Review Date
        System.out.println(this.dateOfReview + " " + this.instructorFirstName + " " + this.instructorLastName + " " + reviewDate);

    }

    public long getDaysForReview() {
        return daysForReview;
    }
    
    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }
    
    public void setDaysForReview(long reviewInDays) {
        daysForReview = reviewInDays;
    }

    public LocalDate getNextReviewDate() {
        System.out.println(nextReviewDate);
        System.out.println(dateOfReview.plusDays(365));
        return nextReviewDate;
    }


    public void setLastReviewScore(int lastReviewScore) {
        this.lastReviewScore = lastReviewScore;
    }
    
    public int getLastReviewScore() {
        return lastReviewScore;
    }

    @Override
    public void setInstructorId(int instructorId) {
        // TODO Auto-generated method stub
        this.instructorId = instructorId;
    }

    @Override
    public int getInstructorId() {
        // TODO Auto-generated method stub
        return instructorId;
    }

    public void setClassroomId(int id){
        this.classroomId = id;
    }
    
    @Override
    public int getInstructorAge() {
        // TODO Auto-generated method stub
        return this.instructorAge;
    }
    
    @Override
    public void setInstructorAge(int instructorAge) {
        // TODO Auto-generated method stub

    }

    public LocalDate getDateOfReview(){
        return this.dateOfReview;
    }

    @Override
    public String getInstructorFirstName() {
        return this.instructorFirstName;
    }

    @Override
    public String getInstructorLastName() {
        return this.instructorLastName;
    }

    public void deleteStudentById(int id){                
        List<Student> students = listStudent
            .stream()
            .filter(i -> i.getInstructorId()!=id)            
            .collect(toList());
        
        this.listStudent = students;        
    }

    public int getSalary(){return this.salary;}

    public void addStudent(Student s){
        listStudent.add(s);
        s.setTid(this.instructorId);
        count++;
    }

    public boolean isEmpty(){
        return count < size;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }
    
    public void showStudents(){
        if(!listStudent.isEmpty()){
            for (Student s: listStudent){
                System.out.println(s.getInstructorFirstName());
            }

        }

    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }

    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        this.instructorFirstName = instructorFirstName;
    }
    
    public int getClassroomId() {
        return classroomId;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDateOfReview(LocalDate dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    @Override
    public String toString() {
        return "Teacher{" + "Id=" + instructorId + ", Age=" + instructorAge + ", LastName=" + instructorLastName + ", FirstName=" + instructorFirstName + ", Wage=" + salary + ", count=" + count + ", size=" + size + '}';
    }
    
    
}

