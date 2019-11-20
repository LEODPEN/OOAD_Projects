package proC.models.ObjectsInBoard;

import proC.models.AllObects;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;
import proC.utils.Observable;
import proC.utils.Observer;
import proC.physicsWorld.Vect;

import java.util.ArrayList;
import java.util.List;

public class Ball implements AllObects, Observable {
    private double x;
    private double y;
    // 球的大小可以变化吗
    private double radius;
    private final BoardObjectTypeEnum type;
    private final String name;
    private Vect velocity;
    // 是否被吸收,是则不再显示
    private boolean isAbsorbed = false;
    private final List<Observer> observers = new ArrayList<>();



    public Ball(double x, double y,double xv, double yv, String name) {
        this.x = x;
        this.y = y;
        radius = Constants.BASE_RADIUS;
        velocity = new Vect(xv, yv);

        type = BoardObjectTypeEnum.BALL;
        this.name = name;

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


    @Override
    public Vect getCenter() {
        return null;
    }

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
    public List<Observer> getObservers() {
        return null;
    }
}
