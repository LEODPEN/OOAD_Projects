package proC.models.buildAndCollision;

import proC.models.ObjectsInBoard.AllObjects;
import proC.physicsWorld.Vect;

import java.io.Serializable;

public class Details implements Serializable {

    private AllObjects toCollide;
    private AllObjects collided;
    private double whenCollisionHappen;
    private Vect velocityAfterCollision;

    private AllObjects toIn;
    private AllObjects in;
    private double whenIn;
    private Vect velocityInRailAndCurve;

    public AllObjects getToIn() {
        return toIn;
    }

    public void setToIn(AllObjects toIn) {
        this.toIn = toIn;
    }

    public double getWhenIn() {
        return whenIn;
    }

    public void setWhenIn(double whenIn) {
        this.whenIn = whenIn;
    }

    public Vect getVelocityInRailAndCurve() {
        return velocityInRailAndCurve;
    }

    public void setVelocityInRailAndCurve(Vect velocityInRailAndCurve) {
        this.velocityInRailAndCurve = velocityInRailAndCurve;
    }

    public AllObjects getIn() {
        return in;
    }

    public void setIn(AllObjects in) {
        this.in = in;
    }

    public AllObjects getToCollide() {
        return toCollide;
    }

    public void setToCollide(AllObjects toCollide) {
        this.toCollide = toCollide;
    }

    public AllObjects getCollided() {
        return collided;
    }

    public void setCollided(AllObjects collided) {
        this.collided = collided;
    }

    public double getWhenCollisionHappen() {
        return whenCollisionHappen;
    }

    public void setWhenCollisionHappen(double whenCollisionHappen) {
        this.whenCollisionHappen = whenCollisionHappen;
    }

    public Vect getVelocityAfterCollision() {
        return velocityAfterCollision;
    }

    public void setVelocityAfterCollision(Vect velocityAfterCollision) {
        this.velocityAfterCollision = velocityAfterCollision;
    }
}
