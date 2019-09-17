package proA.game;

import java.util.Stack;

/**
 * @author onion
 * @date 2019/9/10 -10:05 上午
 */
public class Ant {
    private int currentPoint;
    private int direction;
    private boolean arrived;
    private int speed;

    public Ant(int startPoint, int direction, int speed){
        this.currentPoint = startPoint * 2;
        this.direction = direction; // 1 or -1
        this.arrived = false;
        this.speed = speed;
    }

    public int getDirection(){
        return direction;
    }

    public int getCurrentPoint(){
        return currentPoint;
    }

    public boolean isArrived(){
        return arrived;
    }

    public void changeDirection(){
        direction *= -1;
    }

    public void arrive(){
        this.arrived = true;
    }

    public void move(){
        currentPoint += direction * speed;
    }
}
