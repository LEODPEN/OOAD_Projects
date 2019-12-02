package OOADLab.Lab2;


// subject 被观察
public interface NewsPublisher {

     public void newsNotify(int num);

     public void attach(Subscriber subscriber);

     public void detach(Subscriber subscriber);
}
