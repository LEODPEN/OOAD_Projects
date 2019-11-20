package proC.models.ObjectsInBoard;

import proC.physicsWorld.Angle;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

// 不能旋转
public class PaddleGizmo implements Gizmo {

    private double x;
    private double y;
    private double xWithOffset;
    private double yWithOffset;
//    private double offset;
    // 不抽象为一条线，有固定比例的长和宽
    private double width;
    private double length;
    private final double rCoefficient;
    private BoardObjectTypeEnum type;
    private final String name;
    private double angle;
    // 中点，不转就不用
//    private Vect pivot;
    // 角速度
//    private double angularVelocity;
    private boolean triggered;

    private final List<Observer> observers;

    private boolean keyPressed;
    private boolean keyReleased;



    public PaddleGizmo(double x, double y, BoardObjectTypeEnum type, String name ) {
        this.x = x;
        this.y = y;

        this.xWithOffset = x ;
        this.yWithOffset = y ;


        // 固定长宽 2 * 0.25
        width = Constants.BASE_LENGTH/4;
        length = Constants.BASE_LENGTH*2;



        rCoefficient = 0.95;

        this.name=name;

        this.type = type;
        angle = 0;
        observers = new ArrayList<>();

    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void rotate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void expand() {
        length *= 2;
        width *= 2;
        notifyObservers();
    }

    @Override
    public void setCoordinates(double x, double y) {
        this.xWithOffset += x-this.x;
        this.yWithOffset += y-this.y;

        this.x = x;
        this.y = y;
        notifyObservers();
    }

    @Override
    public void trigger(boolean keyPressed, boolean keyReleased) {
        // todo add some other methods here
        triggered=!triggered;
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
        // todo may do some other methods
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
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Vect getCenter() {
        return new Vect(x + length/2, y+ width/2);
    }


}
