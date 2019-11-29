package proC.models.ObjectsInBoard;

import proC.physicsWorld.*;
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
    // 此处的sides为除去入口和出口之边
    private final List<LineSegment> sides;
    // 入口与出口边
    private final List<LineSegment> deleteGravityLines;
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
        angle = 0.0;
        rCoefficient = 1.0;
        sides = new ArrayList<>();
        corners = new ArrayList<>();
        deleteGravityLines = new ArrayList<>();

    }

    @Override
    public void changeBallVelocityByAngle(Ball ball){
        Vect v = ball.getVelocity();
        double vx = v.x();
        double vy = v.y();
        if (angle==0){
            if (vy>0 || vx <0){
                ball.setVelocity(new Vect(vy,vx));
            }
        }else if (angle==90){
            if (vy<0){
                // 向上
                ball.setVelocity(new Vect(-vy,vx));
            }else if (vx < 0){
                ball.setVelocity(new Vect(vy,-vx));
            }
        }else if (angle==180){
            if (vx>0 || vy <0){
                ball.setVelocity(new Vect(vy,vx));
            }
        }else {
            if (vx>0){
                ball.setVelocity(new Vect(vy,-vx));
            }else if (vy>0){
                ball.setVelocity(new Vect(-vy,vx));
            }
        }
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
        LineSegment s1, s2;
        // 作直角
        s1 = new LineSegment(x,y,x,y+length);
        s2 = new LineSegment(x,y+length,x+length,y+length);
        s1 = Geometry.rotateAround(s1, getCenter(), new Angle(Math.toRadians(angle)));
        s2 = Geometry.rotateAround(s2, getCenter(), new Angle(Math.toRadians(angle)));

        sides.add(s1);
        sides.add(s2);

        return sides;
    }

    @Override
    public List<LineSegment> getDeleteGravityLines() {
        deleteGravityLines.clear();

        LineSegment s1, s2;
        // 0 和 180 没问题
        if (angle==0){
            s1 = new LineSegment(x,y,x+length,y);
            s2 = new LineSegment(x+length,y,x+length,y+length);
        }
        else if (angle == 90){
            s1 = new LineSegment(x,y+length,x+length,y+length);
            s2 = new LineSegment(x+length,y,x+length,y+length);
        }else if (angle == 180){
            s1 = new LineSegment(x,y,x,y+length);
            s2 = new LineSegment(x,y+length,x+length,y+length);
        }else {
            s1 = new LineSegment(x,y,x+length,y);
            s2 = new LineSegment(x,y,x,y+length);
        }

//        s1 = Geometry.rotateAround(s1, getCenter(), new Angle(Math.toRadians(angle)));
//        s2 = Geometry.rotateAround(s2, getCenter(), new Angle(Math.toRadians(angle)));

        deleteGravityLines.add(s1);
        deleteGravityLines.add(s2);

        return deleteGravityLines;
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
