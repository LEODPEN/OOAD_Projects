package proC.models.buildAndCollision;

import javafx.beans.property.SimpleDoubleProperty;
import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.Board;
import proC.models.ObjectsInBoard.Gizmo;
import proC.utils.Constants;

import java.util.List;

// 加减初始化汇总
public class Model {

    private final Board board;


    private double gravity;

    private List<Gizmo> gizmos;

    private List<Ball> balls;


    private final Collision collision;

    // 初始化
    public Model() {
        board = new Board();
        gravity = Constants.GRAVITY;

        // 碰撞引擎初始化
        collision = new Collision(board);
        gizmos = board.getGizmos();
        balls = board.getBalls();
    }

    public void moveBalls() {
        collision.moveBall(Constants.SECONDS_PER_FRAME);

        activateGizmos(); // notify
    }

    private void activateGizmos() {
        for (Gizmo gizmo : gizmos) {
            gizmo.activateAction();
        }
    }

    private boolean isIntersecting(Gizmo gizmo){
        return false;
    }

    private boolean isOutside(Gizmo gizmo){
        return false;
    }
}
