package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class RailGizmo implements Gizmo {

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

    public RailGizmo(double x, double y, BoardObjectTypeEnum type, String name ){

        this.x  = x;
        this.y = y;
        this.type = type;
        this.name = name;
        observers = new ArrayList<>();
        rCoefficient = 1.0;
        sides = new ArrayList<>();
        corners = new ArrayList<>();

    }


    // todo didn't complete


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
    public void shrink(){

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
    public void setCoordinates(double x, double y) {

    }

    @Override
    public void activateAction() {

    }
}
