package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;
import proC.physicsWorld.Vect;

import java.lang.invoke.ConstantBootstraps;
import java.util.ArrayList;
import java.util.List;

public class CirCleGizmo implements Gizmo {

    // 弹性系数需要吗？
    private double x;
    private double y;
    private double radius;

    private final BoardObjectTypeEnum type;
    private final String name;
    private final List<Observer> observers;
    private final List<Circle> sides;
    private final double rCoefficient;
    private double angle;
    private boolean triggered;


    public CirCleGizmo(double x,double y, String name, double radius) {

        // 左下点
        this.x = x;
        this.y = y;

        this.radius = radius;

        this.name = name;

        this.angle = 0;

        this.type = BoardObjectTypeEnum.CIRCLE;

        rCoefficient = 1.0;

        this.observers = new ArrayList<>();

        sides = new ArrayList<>();

        triggered = false;


    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        sides.clear();
        Circle circle = new Circle(x+radius,y+radius,radius);
        sides.add(circle);
        return sides;
    }

    @Override
    public double getRCoefficient() {
        return rCoefficient;
    }

    @Override
    // 不变
    public void rotate() {
//        throw new UnsupportedOperationException();
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
        notifyObservers();
    }

    @Override
    // 只使用了mode1，即笛卡尔坐标系
    public Vect getCenter() {
        return new Vect(x + radius, y + radius);
    }

    @Override
    public void expand() {
        radius *= 2;
        notifyObservers();
    }

    @Override
    public void shrink(){
        if(radius== Constants.BASE_RADIUS)return;
        radius/=2;
        notifyObservers();
    }

    @Override
    public void trigger(boolean keyPressed, boolean keyReleased) {
        triggered = !triggered; // ?
    }

    @Override
    public boolean isTriggered() {
        return triggered;
    }
//   default
//    @Override
//    public boolean getKeyPressed() {
//        return false;
//    }

    @Override
    public void activateAction() {
        notifyObservers();
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
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
}
