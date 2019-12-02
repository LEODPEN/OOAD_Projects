package OOADLab;

import OOADLab.Lab1.GlobalObject;
import OOADLab.Lab2.BusinessNewsPublisher;
import OOADLab.Lab2.EmailSubscriber;
import OOADLab.Lab2.SMSSubscriber;
import OOADLab.Lab2.Subscriber;
import OOADLab.Lab3.DataEntry;

import java.util.Scanner;

public class LabTest {

    public static void main(String[] args){

        // singleton
        testLab1();

        // observer
        testLab2();

        // strategy
        testLab3();
    }


    /*** Strategy ***/
    private static void testLab3(){
        DataEntry dialog = new DataEntry();
        char answer;
        Scanner in =new Scanner(System.in);
        System.out.print("Input type [ (n)umber, (u)pper, (l)ower, e(x)it ]: ");
        answer = in.next().charAt(0);
        while (answer != 'x'){
            try {
                dialog.setValidationType(answer);
                dialog.interact();
            }catch (Exception e){
//                e.printStackTrace();
            }finally {
                System.out.print("Input type [ (n)umber, (u)pper, (l)ower, e(x)it ]: ");
                answer = in.next().charAt(0);
            }
        }
    }

    /*** SingleTon ***/

    private static void testLab1(){
        foo();
        bar();
        GlobalObject globalObject = GlobalObject.getInstance();
        System.out.println("foo: globalObject's value is "+ globalObject.getValue());
    }

    private static void foo(){
        GlobalObject globalObject = GlobalObject.getInstance();
        System.out.println("foo: globalObject's value is "+ globalObject.getValue());
    }

    private static void bar(){
        GlobalObject globalObject = GlobalObject.getInstance();
        globalObject.setValue(42);
        System.out.println("foo: globalObject's value is "+ globalObject.getValue());
    }


    /*** Observer ***/
    private static void testLab2(){
        BusinessNewsPublisher publisher = new BusinessNewsPublisher();
        Subscriber s1 = new SMSSubscriber();
        Subscriber s2 = new EmailSubscriber();
        publisher.attach(s1);
        publisher.attach(s2);
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        publisher.newsNotify(num);

    }


}
