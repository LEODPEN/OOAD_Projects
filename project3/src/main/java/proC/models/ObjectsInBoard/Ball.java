package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observable;
import proC.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Ball implements AllObjects, Observable {
    private double x;
    private double y;

    //记录初始位置
    private final double initialX;
    private final double initialY;

    // 球的大小可以变化吗
    private double radius;
    private final BoardObjectTypeEnum type;

    private final List<Circle> sides;

    private final String name;
    private Vect velocity;
    // 是否被吸收,是则不再显示
    private boolean isAbsorbed = false;
    private final List<Observer> observers;



    public Ball(double x, double y,double xv, double yv, String name) {
        this.x = this.initialX = x;
        this.y = this.initialY = y;
        radius = Constants.BASE_RADIUS;
        // 最开始不要只有重力吧？
        velocity = new Vect(xv, yv);

        observers = new ArrayList<>();
        sides = new ArrayList<>();
        type = BoardObjectTypeEnum.BALL;
        this.name = name;

    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        // 就它本身
        sides.clear();
        sides.add(new Circle(x, y, radius));
        return sides;
    }

    public void moveForTime(double moveTime) {
        x += (velocity.x() * moveTime);
        y += (velocity.y() * moveTime);
        notifyObservers();
    }

    public void expand(){
        radius*=2;
        notifyObservers();
    }

    public void shrink(){
        //不能更小了
        if(radius==Constants.BASE_RADIUS)
            return;
        radius/=2;
        notifyObservers();
    }

    public void rotate(){
        //ball can't rotate
    }


    public void setX(double x) {
        this.x = x;
        notifyObservers();
    }

    public void setY(double y) {
        this.y = y;
        notifyObservers();
    }
    public Vect getVelocity() {
        return velocity;
    }

    public void setVelocity(Vect velocity) {
        double velX = velocity.x();
        double signX = Math.signum(velX);
        double velY = velocity.y();
        double signY = Math.signum(velY);

        velX = Math.max(Math.abs(velX), 0.1);
        velX = Math.min(Math.abs(velX), 200);
        velY = Math.max(Math.abs(velY), 0.1);
        velY = Math.min(Math.abs(velY), 200);

        this.velocity = new Vect(velX * signX, velY * signY);
    }
    public boolean isInAbsorber() {
        return isAbsorbed;
    }

    public void setInAbsorber(boolean isAbsorbed) {
        this.isAbsorbed = isAbsorbed;
    }

    @Override
    public Vect getCenter() {
        return new Vect(x, y);
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

    public double getRadius() {
        return radius;
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    //重置小球的位置（重回设计模式时）
    public void resetCoordinate(){
        setX(initialX);
        setY(initialY);
        setVelocity(new Vect(0,0));

        notifyObservers();
    }

    public void setXVelocity(double xVelocity) {
        setVelocity(new Vect(xVelocity, velocity.y()));
    }
    public void setYVelocity(double yVelocity) {
        setVelocity(new Vect(velocity.x(), yVelocity));
    }
}
