package proC.models.buildAndCollision;

import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.Board;
import proC.physicsWorld.Vect;
import proC.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class Collision {

    private Vect gravity;
    // 计算球的碰撞可能
    private final Map<Ball, Details> collisionDetailsMap;
    private final Board board;

    public Collision(Board board) {
        // board 都只一个
        this.board = board;
        this.gravity = new Vect(0, Constants.GRAVITY);
        this.collisionDetailsMap = new HashMap<>();
    }

    private void applyGravity(Ball ball, double t) { // t : time
        // v = v_0 + gt
        // xy坐标系
        ball.setVelocity(ball.getVelocity().plus(gravity.times(t)));
    }

    void whenCollisionHappen(){
        // 分情况

    }

    void whenBallGizmoCollide(){
        // triangle square circle paddle(2)


    }

    void whenBallWallsCollide(){

    }

    void whenBallBallCollide(){

    }



}
