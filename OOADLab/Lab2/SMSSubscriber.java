package OOADLab.Lab2;

public class SMSSubscriber implements Subscriber {

    private final String name = "SMSSubscriber";

    @Override
    public int update(int num) {
        if (num!=1){
            num+=1;
        }
        System.out.println("I am output from "+name+" update(), "+ num);
        return num;
    }
}
