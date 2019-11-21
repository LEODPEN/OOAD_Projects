package proC.models.ObjectsInBoard;

import proC.models.AllObjects;
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
        this.x = x;
        this.y = y;
        radius = Constants.BASE_RADIUS;
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

    public void setXVelocity(double xVelocity) {
        setVelocity(new Vect(xVelocity, velocity.y()));
    }
    public void setYVelocity(double yVelocity) {
        setVelocity(new Vect(velocity.x(), yVelocity));
    }
}
