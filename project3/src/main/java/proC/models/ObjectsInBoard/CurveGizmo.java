package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class CurveGizmo implements Gizmo {

    private double x;
    private double y;
    private double radius;

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
        // 边的弹性系数
        angle = 0;
        rCoefficient = 1.0;
        sides = new ArrayList<>();
        corners = new ArrayList<>();

    }

    // todo didn't complete
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
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public Vect getCenter() {
        return null;
    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        return null;
    }

    @Override
    public List<Observer> getObservers() {
        return null;
    }

    @Override
    public void rotate() {

    }

    @Override
    public double getRCoefficient() {
        return 0;
    }

    @Override
    public double getAngle() {
        return 0;
    }

    @Override
    public void expand() {

    }

    @Override
    public void shrink() {

    }

    @Override
    public void setCoordinates(double x, double y) {

    }

    @Override
    public void activateAction() {

    }
}
