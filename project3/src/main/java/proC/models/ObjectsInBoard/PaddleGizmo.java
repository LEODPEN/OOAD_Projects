package proC.models.ObjectsInBoard;

import proC.physicsWorld.Angle;
import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
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

    // 不抽象为一条线，有固定比例的长和宽
    private double width;
    private double length;
    private final double rCoefficient;
    private BoardObjectTypeEnum type;
    private final String name;
    private double angle;

    private boolean triggered;

    private final List<Observer> observers;
    private final List<LineSegment> sides;

    private final List<Circle> corners;

    private boolean keyPressed;
    private boolean keyReleased;



    public PaddleGizmo(double x, double y, BoardObjectTypeEnum type, String name ) {
        this.x = x;
        this.y = y;

        // 固定长宽 2 * 0.25
        width = Constants.BASE_LENGTH/4;
        length = Constants.BASE_LENGTH*2;

        rCoefficient = 0.95;

        this.name=name;

        this.type = type;
        angle = 0;
        observers = new ArrayList<>();
        sides = new ArrayList<>();
        corners = new ArrayList<>();

    }

    @Override
    public List<LineSegment> getLines() {
        // 每次sides重新计算
        sides.clear();

        // 四条边
        LineSegment ls1 = new LineSegment(x,y,x+length, y);
        LineSegment ls2 = new LineSegment(x+length,y,x+length, y+width);
        LineSegment ls3 = new LineSegment(x+length,y+width,x, y+width);
        LineSegment ls4 = new LineSegment(x,y+width,x, y);

        // 不会改变，旋转
        sides.add(ls1);
        sides.add(ls2);
        sides.add(ls3);
        sides.add(ls4);

        return sides;
    }



    @Override
    public List<Circle> getCircles() {
        corners.clear();

        Circle c1 = new Circle(x,y,0);
        Circle c2 = new Circle(x+length,y,0);
        Circle c3 = new Circle(x+length,y+width,0);
        Circle c4 = new Circle(x,y+width,0);

        // 不旋转
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
    public void rotate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void expand() {
        // do nothing
    }

    @Override
    public void shrink(){
        //do nothing
    }


    @Override
    public void setCoordinates(double x, double y) {
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
