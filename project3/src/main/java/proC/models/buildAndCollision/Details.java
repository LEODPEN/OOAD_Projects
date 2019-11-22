package proC.models.buildAndCollision;

import proC.models.ObjectsInBoard.AllObjects;
import proC.physicsWorld.Vect;

public class Details {

    private AllObjects toCollide;
    private AllObjects collided;
    private double whenCollisionHappen;
    private Vect velocityAfterCollision;

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
