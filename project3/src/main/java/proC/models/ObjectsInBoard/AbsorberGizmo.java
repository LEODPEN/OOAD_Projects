package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AbsorberGizmo implements Gizmo {

    private double x;
    private double y;

    //  固定每个absorber 只占有一个格子，所以长宽不应该变化
    // 都是一个格子length
    private final double width;

    private final double height;

    private final List<Observer> observers;

    // 边
    private final List<LineSegment> sides;

    private final BoardObjectTypeEnum type;

    // 直接遇到就ball消失，不需要弹性系数？
    private final double rCoefficient;

    private final String name;

    private final boolean triggered;

    // 考虑到同时被吸入的情况
    private final Queue<Ball> balls;

    private double angle = 0;
    private boolean keyPressed;
    private boolean keyReleased;

    public AbsorberGizmo(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        width = Constants.BASE_LENGTH;
        height = Constants.BASE_LENGTH;

        // 直接遇到就ball消失，不需要弹性系数，写上不一定用
        rCoefficient = Double.NEGATIVE_INFINITY;

        balls = new LinkedList<>();
        sides = new ArrayList<>();
        observers = new ArrayList<>();
        triggered = false;
        type = BoardObjectTypeEnum.ABSORBER;

    }

    @Override
    public double getRCoefficient() {
        return rCoefficient;
    }

    @Override
    public List<LineSegment> getLines() {
        // 每次sides重新计算
        sides.clear();

        // 四条边
        LineSegment ls1 = new LineSegment(x,y,x+width, y);
        LineSegment ls2 = new LineSegment(x+width,y,x+width, y+height);
        LineSegment ls3 = new LineSegment(x+width,y+height,x, y+height);
        LineSegment ls4 = new LineSegment(x,y+height,x, y);

        // 不会改变，旋转
        sides.add(ls1);
        sides.add(ls2);
        sides.add(ls3);
        sides.add(ls4);

        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        return null;
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

    @Override
    public Vect getCenter() {
        return new Vect(x + width/2, y + height/2);
    }

    @Override
    public void rotate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAngle() {
        return angle; // always 0
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
    public void trigger(boolean keyPressed, boolean keyReleased) {
        // todo wtf?
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
    // 一直在catch
    public void activateAction() {
        catchBalls();
    }

    private void catchBalls(){
        if (balls.size()>0){
            Ball ball = balls.remove();
            // todo delete the ball or make it cannot be seen
        }
    }

    public Queue<Ball> getBalls() {
        return balls;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
