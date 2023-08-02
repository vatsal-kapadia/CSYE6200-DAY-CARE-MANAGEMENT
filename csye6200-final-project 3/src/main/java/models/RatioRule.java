package models;

public class RatioRule {
    private int low;
    private int high;
    private int sizeOfGroup;
    private int sizeOfClassroom;

    public RatioRule(String csv){
        String[] item = csv.split(",");
        low = Integer.parseInt(item[0]);
        high = Integer.parseInt(item[1]);
        sizeOfGroup = Integer.parseInt(item[2]);
        sizeOfClassroom = Integer.parseInt(item[3]);
    }
    
    public int getSizeOfGroup(){return sizeOfGroup;}
    public int getSizeOfClassroom(){return sizeOfClassroom;}
    public int getLow(){return low;}
    public int getHigh(){return  high;}
    public boolean inRange(int age){
        return (low<=age && age < high);
    }
    public int getNumber(){return sizeOfGroup;}   
}
