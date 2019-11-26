package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class CurveGizmo implements Gizmo {

    private double x;
    private double y;
    private double length;

    private final BoardObjectTypeEnum type;
    private final String name;
    private final List<Observer> observers;
    private final List<LineSegment> sides;
    private final List<Circle> corners;
    private final double rCoefficient;
    private double angle;


    public CurveGizmo(double x, double y, BoardObjectTypeEnum type, String name ){

        this.x  = x;
        this.y = y;
        this.type = type;
        this.name = name;
        observers = new ArrayList<>();
        length = Constants.BASE_LENGTH;
        // 边的弹性系数
        angle = 0;
        rCoefficient = 1.0;
        sides = new ArrayList<>();
        corners = new ArrayList<>();

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
        return new Vect(x + length/2, y + length/2);
    }

    @Override
    public List<LineSegment> getLines() {
        // 其实没有
        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        // 两个？
        return corners;
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void rotate() {
        // 都是围绕中心点在转
        angle += 90;
        angle = angle>=360?angle-360:angle;
        notifyObservers();
    }

    @Override
    public double getRCoefficient() {
        return rCoefficient;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void expand() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shrink() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
        notifyObservers();
    }

    @Override
    public void activateAction() {
        notifyObservers();
    }
}
