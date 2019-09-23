package proA.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author onion
 * @date 2019/9/10 -7:23 下午
 */
//单例模式

public final class Stick {
    private int length;
    private ArrayList<Ant> ants;
    private HashMap<Integer, Ant> occupied;

    public HashMap<Integer, Ant> getOccupied() {
        return occupied;
    }

    public boolean allAntsArrive(){
        for (Ant ant : ants)
            if(!ant.isArrived())
                return false;
        return true;
    }

    public Stick(int length) {
        this.length = length;
        ants = new ArrayList<>();
        occupied = new HashMap<>();
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public boolean reach(Ant a){
        return a.getCurrentPoint() == 0 || a.getCurrentPoint() == 2 * length;
//        return a.getCurrentPoint() == 0 || a.getCurrentPoint() == length;

    }
}
