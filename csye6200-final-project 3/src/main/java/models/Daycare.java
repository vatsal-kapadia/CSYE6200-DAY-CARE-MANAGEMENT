package models;

import Utils.ConversionUtil;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Daycare {
    
    
    private int classId;
    private int teacherId;
    
    private List<Classroom> listClassroom = new ArrayList<>();
    
    private List<RatioRule> rulesRatio = new ArrayList<>();
    private List<Student> listStudent = new ArrayList<>();
    
    private List<Student> IsRenewFalseStudentList = new ArrayList<>();
    private List<AbstractIndividual> listTeacher = new ArrayList<>();
    private List<String> CSVListTeacher = new ArrayList<>();
    private List<Student> IsRenewTrueStudentList = new ArrayList<>();


    public Daycare() {

        teacherId = 0;
        classId = 0;

    }
    
    public void addRatioRule(RatioRule r){
        rulesRatio.add(r);
    }

    public void addToDaycare(Student s){
        listStudent.add(s);

        addNewStudentToClass(s);

    }
    
    private void addNewStudentToClass(Student studentObj){
        if (listClassroom.isEmpty()){
            for (RatioRule ratioObj:rulesRatio){
                if (ratioObj.inRange(studentObj.getInstructorAge())){
                    Classroom classRoomObj = ClassroomLazySingletonFactory.getInstance().getObject(classId++,ratioObj.getLow(),ratioObj.getHigh(), ratioObj.getSizeOfClassroom(), ratioObj.getNumber());
                    Instructor teacherObj = getNextTeacherObj(ratioObj.getSizeOfGroup(),classRoomObj.getClassId());
                    studentObj.setClassroomId(classRoomObj.getClassId());
                    teacherObj.addStudent(studentObj);
                    classRoomObj.addTeacher(teacherObj);
                    for(int i = 0; i < ratioObj.getSizeOfClassroom()-1; i++){
                        classRoomObj.addTeacher(getNextTeacherObj(ratioObj.getSizeOfGroup(),classRoomObj.getClassId()));
                    }
                    listClassroom.add(classRoomObj);
                    return;

                }
            }
        }

        for(Classroom classRoomObj : listClassroom){
            // Iterates through the list of ClassRooms
            if (classRoomObj.inRange(studentObj.getInstructorAge())){
                // For each Teacher in ClassRoom
                for(Instructor teacherObj:classRoomObj.getListTeacher()){
                    if(teacherObj.isEmpty()){
                        // if teacher has space, assign
                        studentObj.setClassroomId(classRoomObj.getClassId());
                        teacherObj.addStudent(studentObj);
                        return;
                    }

                }
            }

        }

        // For case when no classRoom exists
        for (RatioRule ratioObj:rulesRatio){
            if (ratioObj.inRange(studentObj.getInstructorAge())){
                Classroom classRoomObj = ClassroomLazySingletonFactory.getInstance().getObject(classId++,ratioObj.getLow(),ratioObj.getHigh(),ratioObj.getSizeOfClassroom(), ratioObj.getNumber());
                Instructor teacherObj = getNextTeacherObj(ratioObj.getSizeOfGroup(),classRoomObj.getClassId());
                studentObj.setClassroomId(classRoomObj.getClassId());
                teacherObj.addStudent(studentObj);
                classRoomObj.addTeacher(teacherObj);
                for(int i = 0; i < ratioObj.getSizeOfClassroom()-1; i++){
                    classRoomObj.addTeacher(getNextTeacherObj(ratioObj.getSizeOfGroup(),classRoomObj.getClassId()));

                }
                listClassroom.add(classRoomObj);

            }
        }

    }
    
    public Instructor getNextTeacherObj(int size, int cid){
        return new Instructor(CSVListTeacher.get(teacherId), teacherId++, size, cid);
    }

    public void addAllStudentObjectsToCSV(){
        List<String> CSVListObj = new ArrayList<>();        
        for (Student studentObj : listStudent) {
            CSVListObj.add(studentObj.toCSV());
        }        
        ConversionUtil.writingCSVFile(CSVListObj, "Student.txt");
    }
    
    public AbstractIndividual getStudentObjById(int id) {
        for(AbstractIndividual person : listStudent) {
            if(person.getInstructorId() == id)
                return person;
        }
        return null;
    }
     

   
    public void deleteStudentObjById(int studentId) {
        
        List<Student> studentsListObj = listStudent
            .stream()
            .filter(i -> i.getInstructorId()!=studentId)            
            .collect(toList());
        
        this.listStudent = studentsListObj;        
    }
    
    

    public List<Student> getListStudent(){
        return listStudent;
    }

    public List<Classroom> getListClassroom() {
        return listClassroom;
    }

    public void showAll(){
        for (Classroom c: listClassroom){
            c.showTeachers();
        }
    }

    
    public void setCSVListTeacher(List<String> teacherCSVList) {
        CSVListTeacher = teacherCSVList;
    }

    
    public List<Student> getStudentListwithIsNeedRenewTrue(){
       IsRenewTrueStudentList.clear();
       for(Student s: listStudent){
           if(s.isIsRenewRequired() == true)
               IsRenewTrueStudentList.add(s);
       }
        return IsRenewTrueStudentList;
    }

    public List<Student> getStudentListwithIsNeedRenewFalse(){
       IsRenewFalseStudentList.clear();
       for(Student s: listStudent){
           if(s.isIsRenewRequired() == false)
               IsRenewFalseStudentList.add(s);
       }
        return IsRenewFalseStudentList;
    } 
}
