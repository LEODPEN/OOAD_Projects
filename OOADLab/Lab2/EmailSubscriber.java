package OOADLab.Lab2;

public class EmailSubscriber implements Subscriber {

    private final String name = "EmailSubscriber";

    @Override
    public int update(int num) {
        if (num==1){
            num +=1;
        }else {
            num+=2;
        }
        System.out.println("I am output from "+name+" update(), "+ num);
        return num;
    }
}
