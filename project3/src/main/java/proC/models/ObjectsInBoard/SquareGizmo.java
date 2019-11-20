package proC.models.ObjectsInBoard;

import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class SquareGizmo implements Gizmo {

    // 弹性系数需要吗？
    private double x;
    private double y;
    private double width;


    private final BoardObjectTypeEnum type;
    private final String name;
    private final List<Observer> observers;
    private double angle;
    private boolean triggered;


    public SquareGizmo(double x, double y, double width, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.name = name;

        this.type = BoardObjectTypeEnum.SQUARE;
        observers = new ArrayList<>();
        angle = 0;
        triggered = false;
    }


    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void rotate() {
            // 也不需要
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    // 一次放大两倍
    public void expand() {
        width *= 2;
        notifyObservers();
    }

    @Override
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
        notifyObservers();
    }

    @Override
    public void trigger(boolean keyPressed, boolean keyReleased) {
        triggered = !triggered;
    }

    @Override
    public boolean isTriggered() {
        return triggered;
    }

//    @Override
//    public boolean getKeyPressed() {
//        return false;
//    }

    @Override
    public void activateAction() {
        notifyObservers();
    }

    @Override
    public BoardObjectTypeEnum getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    // 左
    public double getX() {
        return x;
    }

    @Override
    // 下
    public double getY() {
        return y;
    }

    @Override
    public Vect getCenter() {
        return new Vect(x + width/2 , y + width/2);
    }
}
