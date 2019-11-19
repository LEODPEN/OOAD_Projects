package proC.models;

import proC.models.ObjectsInBoard.Ball;
import proC.models.ObjectsInBoard.Gizmo;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int width;
    private final int height;
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
}
