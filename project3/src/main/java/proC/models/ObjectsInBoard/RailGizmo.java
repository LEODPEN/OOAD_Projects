package proC.models.ObjectsInBoard;

import proC.physicsWorld.*;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class RailGizmo implements Gizmo {

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

    public RailGizmo(double x, double y, BoardObjectTypeEnum type, String name ){

        this.x  = x;
        this.y = y;
        this.type = type;
        this.name = name;
        observers = new ArrayList<>();
        rCoefficient = 1.0; // 外围
        length = Constants.BASE_LENGTH;
        angle=0.0;
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
        sides.clear();
//        LineSegment s1,s2;
//        if (angle==0||angle==180){
//            s1 = new LineSegment(x, y, x, y+length);
//            s2 = new LineSegment(x+length, y,x+length, y+length);
//        }else {
//            s1 = new LineSegment(x, y, x+length, y);
//            s2 = new LineSegment(x, y+length,x+length, y+length);
//        }
        LineSegment s1 = new LineSegment(x, y, x, y+length);
        LineSegment s2 = new LineSegment(x+length, y,x+length, y+length);

        // 根据中心转多少, 返回新边
        s1 = Geometry.rotateAround(s1, getCenter(), new Angle(Math.toRadians(angle)));
        s2 = Geometry.rotateAround(s2, getCenter(), new Angle(Math.toRadians(angle)));

        sides.add(s1);
        sides.add(s2);

        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        return corners;
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void rotate() {
        // 和三角形一样旋转
        angle += 90;
        angle = angle>=360?angle-360:angle;
        notifyObservers();
    }

    @Override
    public void shrink(){
        throw new UnsupportedOperationException();
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
