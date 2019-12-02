package OOADLab.Lab2;

import java.util.ArrayList;
import java.util.List;

public class BusinessNewsPublisher implements NewsPublisher {

    private List<Subscriber> observerList = new ArrayList<>();

    @Override
    public void newsNotify(int num) {
        for (Subscriber s : observerList){
            s.update(num);
        }
    }

    @Override
    public void attach(Subscriber subscriber) {
        observerList.add(subscriber);
    }

    @Override
    public void detach(Subscriber subscriber) {
        observerList.remove(subscriber);
    }
}
