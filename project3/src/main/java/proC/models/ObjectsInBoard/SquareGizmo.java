package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
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
    private final List<LineSegment> sides;
    private final List<Circle> corners;

    private double angle;

    private final double rCoefficient;


    public SquareGizmo(double x, double y, double width, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.name = name;

        this.type = BoardObjectTypeEnum.SQUARE;
        observers = new ArrayList<>();
        sides = new ArrayList<>();
        corners = new ArrayList<>();

        rCoefficient = 1.0;
        angle = 0;
    }

    @Override
    public List<LineSegment> getLines() {

        sides.clear();

        // 顺时针
        LineSegment ls1 = new LineSegment(x,y,x+width, y);
        LineSegment ls2 = new LineSegment(x+width,y,x+width, y+width);
        LineSegment ls3 = new LineSegment(x+width,y+width,x, y+width);
        LineSegment ls4 = new LineSegment(x,y+width,x, y);
        sides.add(ls1);
        sides.add(ls2);
        sides.add(ls3);
        sides.add(ls4);

        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        corners.clear();

        //  params： circle，中点，旋转角度
        // 统一使用顺时针加入
        Circle c1 = new Circle(x, y, 0);
        Circle c2 = new Circle(x+width, y, 0);
        Circle c3 = new Circle(x+width, y+width, 0);
        Circle c4 = new Circle(x, y+width, 0);
        corners.add(c1);
        corners.add(c2);
        corners.add(c3);
        corners.add(c4);

        return corners;
    }

    @Override
    public double getRCoefficient() {
        return rCoefficient;
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    // 转90度等于没变
    public void rotate() {
//        throw new UnsupportedOperationException();
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
    public void shrink(){
        // 不能再小
        if(width== Constants.BASE_LENGTH)return;
        width/=2;
        notifyObservers();
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
