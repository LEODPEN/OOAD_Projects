package proC.models.buildAndCollision;

import javafx.beans.property.SimpleDoubleProperty;
import proC.models.ObjectsInBoard.*;
import proC.physicsWorld.Geometry;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectOperationEnum;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

// 加减初始化汇总
public class Model implements Serializable {

    private final Board board;

    private double gravity;

    private List<Gizmo> gizmos;

    private List<Ball> balls;

    // ball
    private int bCount;

    // circle
    private int ciCount;

    // curve
    private int cuCount;

    // rail
    private int rCount;

    // absorber
    private int aCount;

    // square
    private int sCount;

    // triangle
    private int tCount;

    private final Collision collision;

    // 初始化
    public Model() {
        board = new Board();
        gravity = Constants.GRAVITY;

        // 碰撞引擎初始化
        collision = new Collision(board);
        gizmos = board.getGizmos();
        balls = board.getBalls();

        bCount=ciCount=cuCount=rCount=tCount=sCount=aCount = 0;
    }

    public void moveBalls() {
        collision.moveBall(Constants.SECONDS_PER_FRAME);

        activateGizmos(); // notify
    }

    // 操作

    public boolean shrinkBall(String name){
        Ball ball = getBall(name);
        return ball!=null && shrinkBall(ball.getX(),ball.getY());
    }

    public boolean shrinkBall(double x, double y){
        Ball ball = getBall(x,y);
        if (ball!=null){
            ball.shrink();
            return true;
        }
        return false;
    }

    public boolean shrinkGizmo(String name){
        Gizmo gizmo = getGizmo(name);
        return gizmo !=null && shrinkGizmo(gizmo.getX(),gizmo.getY());
    }

    public boolean shrinkGizmo(double x,double y){
        Gizmo gizmo = getGizmo(x,y);
        if(gizmo != null){
            if(gizmo.getType() != BoardObjectTypeEnum.SQUARE &&
                    gizmo.getType() != BoardObjectTypeEnum.TRIANGLE &&
                    gizmo.getType() != BoardObjectTypeEnum.CIRCLE){
                return false;
            }
            gizmo.shrink();
            return true;
        }
        return false;
    }


    public boolean expandBall(String name){
        Ball ball = getBall(name);
        return ball!=null && expandBall(ball.getX(),ball.getY());
    }


    public boolean expandBall(double x, double y){
        Ball ball = getBall(x,y);
        if (ball!=null){
            ball.expand();
            return true;
        }
        return false;
    }

    public boolean expandGizmo(String name){
        // 用名字来放
        Gizmo gizmo = getGizmo(name);
        return gizmo !=null && expandGizmo(gizmo.getX(),gizmo.getY());
    }

    public boolean expandGizmo(double x , double y){
        Gizmo gizmo = getGizmo(x,y);
        if(gizmo != null){
            if(gizmo.getType() != BoardObjectTypeEnum.SQUARE &&
                    gizmo.getType() != BoardObjectTypeEnum.TRIANGLE &&
                    gizmo.getType() != BoardObjectTypeEnum.CIRCLE){
                return false;
            }
            gizmo.expand();
            return true;
        }
        return false;
    }

    public boolean rotateGizmo(String name) {
        Gizmo gizmo = getGizmo(name);
        return gizmo != null && rotateGizmo(gizmo.getX(), gizmo.getY());
    }

    public boolean rotateGizmo(double x, double y) {
        Gizmo gizmo = getGizmo(x,y);
        // 再次判断
        if(gizmo != null){
            if(gizmo.getType() != BoardObjectTypeEnum.RAIL &&
                    gizmo.getType() != BoardObjectTypeEnum.TRIANGLE &&
                    gizmo.getType() != BoardObjectTypeEnum.CURVE){
                return false;
            }
            gizmo.rotate();
            return true;
        }
        return false;
    }

    public void clearBoard() {
        // 全部删除
        board.reset();
    }


    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {

        collision.setGravity(gravity);
        this.gravity = gravity;
    }

    public Gizmo addGizmo(double x, double y, BoardObjectTypeEnum type) {
        Gizmo gizmo;
//        if (!name.equals("")) {
//            return null;
//        }

        switch (type) {
            case CIRCLE:
                gizmo = new CirCleGizmo(x, y, type.getName()+ciCount++,Constants.BASE_RADIUS);
                break;
            case SQUARE:

                gizmo = new SquareGizmo(x, y, Constants.BASE_LENGTH, type.getName()+sCount++);
                break;
            case TRIANGLE:
                gizmo = new TriangleGizmo(x, y, Constants.BASE_LENGTH, type.getName()+tCount++);
                break;
            case LEFT_PADDLE:
            case RIGHT_PADDLE:
                if(getGizmo(type.getName())!=null)return null;
                gizmo = new PaddleGizmo(x, y, type, type.getName());
                break;
            case CURVE:

                gizmo = new CurveGizmo(x, y,  type, type.getName()+cuCount++);
                break;
            case RAIL:

                gizmo = new RailGizmo(x, y,  type, type.getName()+rCount++);
                break;
            case ABSORBER:
                gizmo = new AbsorberGizmo(x,y, type.getName()+aCount++);
                break;
            default:
                return null;
        }

//        if(isOutside(gizmo)){
//            // 出界
//            return false;
//        }
//        if(isIntersecting(gizmo)) {
//            // 重合了
//            return false;
//        }

        board.addOneGizmo(gizmo);
        return gizmo;
    }

    public Ball addBall(double x, double y, double xv, double yv) {

        // 已经有名字了
        Ball ball = new Ball(x, y, xv, yv, BoardObjectTypeEnum.BALL.getName()+bCount++);
        collision.addBall(ball);
        board.addOneBall(ball);
        if(isBallIntersecting(ball)){
            // 重合了, 都删
            collision.removeBall(ball); // 虽然就一个球
            board.removeOneBall(ball);
            return null;
        }
        return ball;
    }

    public boolean removeGizmo(String name) {
        Gizmo gizmo = getGizmo(name);
        return gizmo != null && removeGizmo(gizmo.getX(), gizmo.getY());
    }

    public boolean removeGizmo(double x, double y) {
        Gizmo gizmo = getGizmo(x,y);
        if (gizmo != null) {
            board.removeOneGizmo(gizmo);
            return true;
        }
        return false;
    }

    public boolean removeBall(String name) {
        Ball ball = getBall(name);
        return ball != null && removeBall(ball.getX(), ball.getY());
    }

    public boolean removeBall(double x, double y) {
        Ball ball = getBall(x,y);
        if (ball != null) {
            board.removeOneBall(ball);
            return true;
        }
        return false;
    }

    private void activateGizmos() {
        for (Gizmo gizmo : gizmos) {
            gizmo.activateAction();
        }
    }

    // 从名字找
    public Gizmo getGizmo(String name) {
        for(Gizmo gizmo : board.getGizmos()) {
            if(gizmo.getName().equals(name)){
                return gizmo;
            }
        }
        return null;
    }

    public Ball getBall(String name) {
        for(Ball ball : board.getBalls()) {
            if(ball.getName().equals(name)){
                return ball;
            }
        }
        return null;
    }

    // 从坐标找
    public Gizmo getGizmo(double x, double y) {
        for(Gizmo gizmo : board.getGizmos()) {
            if(gizmo.getX() == x && gizmo.getY() == y){
                return gizmo;
            }
        }
        return null;
    }

    public Ball getBall(double x, double y) {
        for(Ball ball : board.getBalls()) {
            if (Math.pow(x - ball.getX(),2) + Math.pow(y - ball.getY(), 2) < Math.pow(ball.getRadius(), 2)){
                return ball;
            }
        }
        return null;
    }

    // 运动是否碰撞
    private boolean ballMovingIntersectionCheck(double x, double y, Ball ball){
        ball.setVelocity(new Vect(x,y));
        collision.whenCollisionHappen();
        // 最近碰撞
        if (collision.getCollisionDetails(ball).getWhenCollisionHappen() < Constants.SECONDS_PER_FRAME) {
            return true;
        }

        // 向上取整数,搞到格子里面
        Gizmo gizmo = getGizmo(Math.floor(ball.getX()), Math.floor(ball.getY()));

        if (gizmo != null) {
            // 正方形直接就碰撞
            if (gizmo.getType() == BoardObjectTypeEnum.SQUARE) {
                return true;
            }
            // 圆形则判断下距离
            if (gizmo.getType() == BoardObjectTypeEnum.CIRCLE) {
                double distance = Math.sqrt(Geometry.distanceSquared(ball.getCenter(), gizmo.getCenter()));
                if (distance < ((ball.getRadius()) + (Constants.BASE_LENGTH/2))) {
                    return true;
                }
            }
//            if (gizmo.getType() == BoardObjectTypeEnum.RAIL){
//                return false;
//            }
        }
        return false;
    }

    private boolean isBallIntersecting(Ball ball){
        Vect ov = new Vect(ball.getVelocity().x(),ball.getVelocity().y());

        // 放置的时候检测是否碰撞

        // LEFT
        if(ballMovingIntersectionCheck(0.01,0.0, ball)){
            return true;
        }
        // RIGHT
        if(ballMovingIntersectionCheck(-0.01,0.0, ball)){
            return true;
        }
        // DOWN
        if(ballMovingIntersectionCheck(0.0, -0.01, ball)){
            return true;
        }
        // DOWN
        if(ballMovingIntersectionCheck(0.0, 0.01, ball)){
            return true;
        }

        ball.setVelocity(ov);

        return false;
    }

    //根据挡板类型和方向控制相应挡板的移动
    //todo：挡板间碰撞？
    public void movePaddle(BoardObjectTypeEnum type,BoardObjectOperationEnum operation){

        PaddleGizmo leftPaddle= (PaddleGizmo) getGizmo(BoardObjectTypeEnum.LEFT_PADDLE.getName());
        PaddleGizmo rightPaddle=(PaddleGizmo)getGizmo(BoardObjectTypeEnum.RIGHT_PADDLE.getName());

        boolean isLeftPaddle=type==BoardObjectTypeEnum.LEFT_PADDLE;
        boolean isRightPaddle=type==BoardObjectTypeEnum.RIGHT_PADDLE;

        if(leftPaddle!=null&&isLeftPaddle){
            if(operation==BoardObjectOperationEnum.MOVE_LEFT)
                leftPaddle.moveLeft();
            else if(operation==BoardObjectOperationEnum.MOVE_RIGHT)
                leftPaddle.moveRight();
        }

        if(rightPaddle!=null&&isRightPaddle){
            if(operation==BoardObjectOperationEnum.MOVE_LEFT)
                rightPaddle.moveLeft();
            else if(operation==BoardObjectOperationEnum.MOVE_RIGHT)
                rightPaddle.moveRight();
        }

    }

    public void resetBallAndPaddleCoordinate(){

        for (Gizmo gizmo : board.getGizmos()) {
            if(gizmo instanceof PaddleGizmo){
                ((PaddleGizmo) gizmo).resetCoordinate();
            }
        }
        //todo：多个球？
        if(board.getBalls().isEmpty())return;
        Ball ball=board.getBalls().get(0);
        ball.resetCoordinate();

    }
    // 放置是否碰撞
    private boolean isIntersecting(Gizmo gizmo){
        return false;
    }

    private boolean isOutside(Gizmo gizmo){
        return false;
    }
}
