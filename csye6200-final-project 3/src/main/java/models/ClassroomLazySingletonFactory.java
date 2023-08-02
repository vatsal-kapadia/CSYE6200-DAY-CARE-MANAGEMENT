package models;

public class ClassroomLazySingletonFactory extends AbstractFactory{
    private ClassroomLazySingletonFactory(){

    }
    private static ClassroomLazySingletonFactory instance = null;
    public static ClassroomLazySingletonFactory getInstance(){
        if(instance == null){
            instance = new ClassroomLazySingletonFactory();
        }
        return instance;
    }

    public Classroom getObject(int classId,int low,int high, int classSize, int classroomNumber){
        return new Classroom(classId,low,high,classSize,classroomNumber);
    }

}
