package proC.models.ObjectsInBoard;

import proC.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final double width;
    private final double height;
    private final Walls walls;
    private final List<Ball> balls;
    private final List<Gizmo> gizmos;


    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = new Walls();
        this.balls = new ArrayList<>();
        this.gizmos = new ArrayList<>();
    }

    public Board() {
        this.width = Constants.BOARD_WIDTH;
        this.height = Constants.BOARD_WIDTH;
        this.walls = new Walls();
        this.balls = new ArrayList<>();
        this.gizmos = new ArrayList<>();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Walls getWalls() {
        return walls;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Gizmo> getGizmos() {
        return gizmos;
    }

    public void reset(){
        balls.clear();
        gizmos.clear();
    }

    public boolean addOneGizmo(Gizmo gizmo){
        return gizmos.add(gizmo);
    }

    public boolean addOneBall(Ball ball){
        return balls.add(ball);
    }

    public boolean removeOneBall(Ball ball){
        return balls.remove(ball);
    }

    public boolean removeOneGizmo(Gizmo gizmo){
        return gizmos.remove(gizmo);
    }



}
