package proC.fxgl.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.geometry.Point2D;
import proC.fxgl.ComponentType;
import proC.fxgl.Direction;

/**
 * @author onion
 * @date 2019/11/18 -7:38 下午
 */
public class BallCurveHandler extends CollisionHandler {
    public BallCurveHandler() {
        super(ComponentType.BALL, ComponentType.CURVE);
    }

    @Override
    protected void onCollisionBegin(Entity ball, Entity curve) {
        Direction dir = curve.getObject("direction");
        Point2D point2D = ball.getObject("velocity");
        //todo
//        switch (dir){
//            case LEFT_UP:
//                ball.setProperty("velocity", new Point2D(point2D.getY(), point2D.getX()));
//                break;
//            case RIGHT_DOWN:
//                ball.setProperty("velocity", new Point2D(-point2D.getY(), point2D.getX()));
//                break;
//            case RIGHT_UP:
//                ball.setProperty("velocity", new Point2D(point2D.getY(), point2D.getX()));
//                break;
//            case LEFT_DOWN:
//                ball.setProperty("velocity", new Point2D(point2D.getY(), point2D.getX()));
//                break;
//        }

    }
}
