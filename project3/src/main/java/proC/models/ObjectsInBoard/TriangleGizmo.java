package proC.models.ObjectsInBoard;

import javafx.fxml.FXML;
import proC.physicsWorld.*;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TriangleGizmo implements Gizmo{

    // 弹性系数需要吗？
    private double x;
    private double y;
    // 腰长，非底边
    private double side;
    List<LineSegment> lines;
    List<Circle> corners;


    private final BoardObjectTypeEnum type;
    private final String name;
    private final List<Observer> observers;
    private final List<LineSegment> sides;
    private final double rCoefficient;
    private double angle;
    private boolean triggered;


    public TriangleGizmo(double x, double y, double side,String name) {
        this.x = x;
        this.y = y;
        this.side = side; // 边长/腰
        this.name = name;

        type = BoardObjectTypeEnum.TRIANGLE;
        angle = 0;
        rCoefficient = 1.0;
        triggered = false;
        observers = new ArrayList<>();
        sides = new ArrayList<>();
        corners = new ArrayList<>();
    }

    @Override
    public List<LineSegment> getLines() {
        sides.clear();

        // 顺时针
        LineSegment ls1 = new LineSegment(x, y, x, y+side);
        LineSegment ls2 = new LineSegment(x, y,x+side, y+side);
        LineSegment ls3 = new LineSegment(x+side,y+side, x, y+side);

        // 根据中心转多少, 返回新边
        ls1 = Geometry.rotateAround(ls1, getCenter(), new Angle(Math.toRadians(angle)));
        ls2 = Geometry.rotateAround(ls2, getCenter(), new Angle(Math.toRadians(angle)));
        ls3 = Geometry.rotateAround(ls3, getCenter(), new Angle(Math.toRadians(angle)));

        sides.add(ls1);
        sides.add(ls2);
        sides.add(ls3);

        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        corners.clear();

        Circle c1 = new Circle(x, y, 0);// 左上角
        Circle c2 = new Circle(x, y+side, 0); //左下角
        Circle c3 = new Circle(x+side, y+side, 0); // 右下角

        //  参数： circle，中点，旋转角度
        c1 = Geometry.rotateAround(c1, new Vect(x + side/2, y + side/2), new Angle(Math.toRadians(angle)));
        c2 = Geometry.rotateAround(c2, new Vect(x + side/2, y + side/2), new Angle(Math.toRadians(angle)));
        c3 = Geometry.rotateAround(c3, new Vect(x + side/2, y + side/2), new Angle(Math.toRadians(angle)));

        corners.add(c1);
        corners.add(c2);
        corners.add(c3);

        return corners;
    }

    @Override
    public double getRCoefficient() {
        return rCoefficient;
    }

    @Override
    // 与view将绑定
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

    public double getSide(){return side;}

    @Override
    public Vect getCenter() {
        return new Vect(x + side/2, y + side/2);
    }

    @Override
    // todo 三角形可以操作 顺时针旋转90度
    public void rotate() {
        angle += 90;
        angle = angle>=360?angle-360:angle;
        notifyObservers();
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    // 一次扩大两倍
    public void expand() {
        side *=2;
        notifyObservers();
    }

    @Override
    public void shrink(){
        //不能再小
        if(side== Constants.BASE_LENGTH)return;
        side/=2;
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
}
