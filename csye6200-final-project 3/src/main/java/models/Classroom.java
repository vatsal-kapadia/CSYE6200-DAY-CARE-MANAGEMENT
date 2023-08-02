package models;

import java.util.ArrayList;
import java.util.List;

public class Classroom {

    private int classSize;
    private int classId;
    private List<Instructor> listTeacher = new ArrayList<>();
    private int classroomNumber;
    private int count;
    private int[] rangeOfAge = new int[2];

    public Classroom(int id, int low,int high,int size, int number){
        this.classId = id;
        this.rangeOfAge[0] = low;
        this.rangeOfAge[1] = high;
        this.classSize = size;
        this.classroomNumber=number;
    }

    public int getClassroomNumber() {
        return this.classroomNumber;
    }
    
    public int getCount(){
        return this.count;
    }

    public int[] getRangeOfAge(){
        return this.rangeOfAge;
    }

    public int getSize(){
        return this.classSize;
    }
    public int getClassId(){
        return this.classId;
    }

    public void addTeacher(Instructor t){
        listTeacher.add(t);
        count++;
    }
    
    public boolean inRange(int age){
        return this.rangeOfAge[0]<=age && age<this.rangeOfAge[1];
    }

    public boolean isEmpty(){
        return this.count < this.classSize;
    }

    public List<Instructor> getListTeacher(){
        return  this.listTeacher;
    }
    
     public void showTeachers(){
        if(!listTeacher.isEmpty()){
            for (Instructor teacherObj: listTeacher){
                teacherObj.showStudents();
                System.out.println(teacherObj.getInstructorId());
            }
        }

    }

    public void setAgeRange(int low, int high){
        rangeOfAge[0] = low;
        rangeOfAge[1] = high;
    }

}
