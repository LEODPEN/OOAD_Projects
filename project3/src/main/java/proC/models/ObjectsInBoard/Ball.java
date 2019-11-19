package proC.models.ObjectsInBoard;

import proC.models.AllObects;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Observable;
import proC.utils.Observer;
import proC.physicsWorld.Vect;

import java.util.List;

public class Ball implements AllObects, Observable {

    @Override
    public Vect getCenter() {
        return null;
    }

    @Override
    public BoardObjectTypeEnum getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void subscribe(Observer observer) {

    }

    @Override
    public void unsubscribe(Observer observer) {

    }

    @Override
    public List<Observer> getObservers() {
        return null;
    }
}
