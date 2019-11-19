package proC.utils;

import java.util.List;

public interface Observable {

    default void notifyObservers() {
        for (Observer observer : getObservers()) {
            observer.update();
        }
    }

    default void subscribe(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        if (!getObservers().contains(observer)) {
            getObservers().add(observer);
        }
    }

    // 大概率不会用到
    default void unsubscribe(Observer observer) {
        getObservers().remove(observer);
    }

    List<Observer> getObservers();
}
