package proC.models.buildAndCollision;

import proC.models.ObjectsInBoard.*;
import proC.physicsWorld.Circle;
import proC.physicsWorld.Geometry;
import proC.physicsWorld.LineSegment;
import proC.physicsWorld.Vect;
import proC.type.BoardObjectTypeEnum;
import proC.utils.Constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collision implements Serializable {

    private Vect gravity;
    // 计算球的碰撞可能, 目前就一个球?
    private final Map<Ball, Details> details;
    private final Board board;

    public Collision(Board board) {
        // board 都只一个
        this.board = board;
        this.gravity = new Vect(0, Constants.GRAVITY);
        this.details = new HashMap<>();
    }

    public void setGravity(double y){
        this.gravity = new Vect(0, y);
    }

    private void applyGravity(Ball ball, double t) { // t : time
        // v = v_0 + gt
        // xy坐标系
        ball.setVelocity(ball.getVelocity().plus(gravity.times(t)));
    }

    public void addBall(Ball ball){
        // 为多球准备
        details.put(ball, new Details());
    }

    public void removeBall(Ball ball){
        details.remove(ball);
    }

    public Details getCollisionDetails(Ball ball) {
        return details.get(ball);
    }

    // 启动！一帧一帧动s
    public void moveBall(double moveTime){
        whenCollisionHappen();
        for (Ball ball : board.getBalls()) {
            Details details = getCollisionDetails(ball);
            details.setCollided(null);

            if (!ball.isInAbsorber()) {
                // 还没被吸收
                // move时间更小，还没撞上
                if (details.getWhenCollisionHappen() > moveTime) {
                    ball.moveForTime(moveTime);
                    // 进轨道
                    if (details.getToCollide()!=null && details.getToCollide().getType() == BoardObjectTypeEnum.RAIL && details.getWhenCollisionHappen() - moveTime<0.1){
                        ball.setInRailOrCurve(true);
                    }
                    // 出轨道
                    if ( details.getCollided()!=null && details.getCollided().getType() == BoardObjectTypeEnum.RAIL && details.getWhenCollisionHappen() - moveTime > 1){
                        ball.setInRailOrCurve(false);
                    }
                    if (ball.isInRailOrCurve()){
                        // 改变重力
                        setGravity(0);
                    }
                    else {
                        setGravity(Constants.GRAVITY);
                    }

                    System.out.println(gravity);
                    applyGravity(ball, moveTime);
                } else {
                    if (details.getToCollide() != null) {
                        if (details.getToCollide().getType() == BoardObjectTypeEnum.ABSORBER) {
                            // absorber 逮住球
                            ((AbsorberGizmo) details.getToCollide()).catchBalls(ball);
                        }
                        if (details.getToCollide().getType()== BoardObjectTypeEnum.RAIL || details.getToCollide().getType()== BoardObjectTypeEnum.CURVE){
//                            if (!ball.isInRailOrCurve()){
//                                applyGravity(ball, details.getWhenCollisionHappen());
//                            }
                            // 只算碰撞一次
                            if (details.getCollided()==details.getToCollide() && ball.isInRailOrCurve()){
                                setGravity(0);
                            }else if (details.getCollided()==details.getToCollide()) {
                                setGravity(Constants.GRAVITY);
                            }
                            else if (ball.isInRailOrCurve()){
                                // rail 内但是还没撞下一个rail
                                setGravity(0);
                                details.setCollided(details.getToCollide());
                            }
                            else {
                                // 没撞且不在rail内
                                setGravity(Constants.GRAVITY);
                                details.setCollided(details.getToCollide());
                            }
                        }else {
                            setGravity(Constants.GRAVITY);
                            details.setCollided(details.getToCollide());
                        }
                        ball.moveForTime(details.getWhenCollisionHappen());
                        ball.setVelocity(details.getVelocityAfterCollision());
                        applyGravity(ball, details.getWhenCollisionHappen());

                    }else {
                        setGravity(Constants.GRAVITY);
                        ball.moveForTime(details.getWhenCollisionHappen());
                        ball.setVelocity(details.getVelocityAfterCollision());
                        applyGravity(ball, details.getWhenCollisionHappen());
                    }

                }
            }
        }

    }


    // 将碰撞时间设置
    void whenCollisionHappen(){
        // 分情况
        List<Ball> allBalls = board.getBalls();
        // 初始化时间
        for (Ball ball : allBalls){
            getCollisionDetails(ball).setWhenCollisionHappen(Double.MAX_VALUE);
        }

        // 真实设置时间
        for (Ball ball : allBalls){

            for (Gizmo gizmo : board.getGizmos()){
                if (getCollisionDetails(ball).getCollided()==gizmo && gizmo instanceof RailGizmo && ball.isInRailOrCurve())continue;
                whenBallGizmoCollide(ball, gizmo);
            }

            whenBallWallsCollide(ball,board.getWalls());
        }
    }


    void whenBallGizmoCollide(Ball ball, Gizmo gizmo){
        // triangle square circle paddle(2) curve rail
        double time;
        // 当前没有搞curve 和 trail
        List<LineSegment> lines = gizmo.getLines();
        List<Circle> circles = gizmo.getCircles();
        Vect velocity;
        Details details = getCollisionDetails(ball);

        double whenCollide = details.getWhenCollisionHappen();
        // circle 即为本身大小
        Circle ballCircle = ball.getCircles().get(0);

        if (gizmo.getType() == BoardObjectTypeEnum.RAIL ) {

            // rail只有两条线?
            for (LineSegment line: lines){
                time = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelocity());
//                System.out.println(time);
                if (time < whenCollide) {
                    whenCollide = time;
//                    ball.setInRailOrCurve(false);
                    velocity = Geometry.reflectWall(line, ball.getVelocity(), gizmo.getRCoefficient());
                    details.setVelocityAfterCollision(velocity);
                    details.setToCollide(gizmo);
                }
            }

            List <LineSegment> deleteGravityLines = (List<LineSegment>) ((RailGizmo)gizmo).getDeleteGravityLines();
            for (LineSegment line : deleteGravityLines){
//                double distance;
//                if (ball.isInRailOrCurve()){
//                    if (((RailGizmo)gizmo).getAngle()==0 || ((RailGizmo)gizmo).getAngle()==180){
//                        distance =  Math.abs(ball.getCenter().y()-line.p1().y());
//                    }else {
//                        distance = Math.abs(ball.getCenter().x()-line.p1().x());
//                    }
//                    if (distance<Constants.BASE_LENGTH/2){
////                        details.setCollided();
//                        continue;
//                    }
//                }
                // todo improve and fix bugs
                time = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelocity());
                System.out.println(time);
                if (time < whenCollide){
//                    // xywy
//                    if (whenCollide - time<0.1){
//                        ball.setInRailOrCurve(true);
//                    }
                    whenCollide = time;
                    details.setToCollide(gizmo);
                    // 不变
                    System.out.println(ball.getVelocity());
                    details.setVelocityAfterCollision(ball.getVelocity());
                }
            }


        }else if (gizmo.getType() == BoardObjectTypeEnum.CURVE){
                ball.setInRailOrCurve(true);
        }
        else {

            for (LineSegment line : lines) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelocity());
                if (time < whenCollide) {
                    whenCollide = time;
                    ball.setInRailOrCurve(false);
                    velocity = Geometry.reflectWall(line, ball.getVelocity(), gizmo.getRCoefficient());
                    details.setVelocityAfterCollision(velocity);
                    details.setToCollide(gizmo);
                }
            }

            for (Circle circle : circles) {
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ball.getVelocity());
                if (time < whenCollide) {
                    whenCollide = time;
                    velocity = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), ball.getVelocity(), gizmo.getRCoefficient());
                    details.setVelocityAfterCollision(velocity);
                    details.setToCollide(gizmo);
                }
            }
        }

        details.setWhenCollisionHappen(whenCollide);
    }

    void whenBallWallsCollide(Ball ball,Walls walls){
        List<LineSegment> linesOfWalls = walls.getLines();
        List<Circle> cornersOfWalls = walls.getCircles();
        Details details = getCollisionDetails(ball);
        Circle circleOfBall = ball.getCircles().get(0); // 唯一一个
        double whenCollide = details.getWhenCollisionHappen();
        Vect velocity;
        double time;

        // 与墙撞
        for (LineSegment line : linesOfWalls) {
            // 碰撞时间检测
            // 如果不碰返回 POSITIVE_INFINITY
            time = Geometry.timeUntilWallCollision(line, circleOfBall, ball.getVelocity());
            if (time < whenCollide) {
                whenCollide = time;
                // 碰后速度
                velocity = Geometry.reflectWall(line, ball.getVelocity(), walls.getRCoefficient());
                details.setVelocityAfterCollision(velocity);
                details.setToCollide(walls);
            }
        }

        // 与角撞
        for (Circle corner : cornersOfWalls) {
            time = Geometry.timeUntilCircleCollision(corner, circleOfBall, ball.getVelocity());
            if (time < whenCollide) {
                whenCollide = time;
                velocity = Geometry.reflectCircle(corner.getCenter(), ball.getCenter(), ball.getVelocity(), walls.getRCoefficient());
                details.setVelocityAfterCollision(velocity);
                details.setToCollide(walls);
            }
        }

        // 总是最近被撞时间
        details.setWhenCollisionHappen(whenCollide);
    }

    void whenBallBallCollide(){
        // todo add more balls
    }



}
