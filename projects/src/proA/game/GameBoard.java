package proA.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author onion
 * @date 2019/9/10 -8:13 下午
 */
public class GameBoard {
    private int min = 0;
    private int max = 0;
    private static final int defaultLength = 27;
    private static final int defaultSpeed = 1;
    private static final int[] defaultPositions = {3, 7, 11, 18, 23};
    private int maxState = 0;
    private int minState = 0;
    private ArrayList<Ant> ants;
    private ArrayList<PositionInfo>[][] traceList;

    public GameBoard(){}

    public GameBoard(ArrayList<Ant> ants){
        this.ants = ants;
    }
    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public void run(){
        run(defaultPositions, defaultSpeed, defaultLength);
    }
    public void run(int []positions, int speed, int length) {
        // 判断参数是否合法
        for (int position : positions)
            if(position % speed != 0 || position >= length || position <= 0 || length % speed != 0)
                throw new IllegalArgumentException("游戏设定的参数不合理");

        // 初始化路径数组
        int numOfAnts = positions.length;
        traceList = new ArrayList[1 << numOfAnts][numOfAnts];
        // 计算min和max
        for (int position : positions) {
            min = Math.max(min, Math.min(position, length - position)/speed);
            max = Math.max(max, Math.max(position, length - position)/speed);
        }
        // 枚举蚂蚁的所有状态
//        int debugMin = Integer.MAX_VALUE;
//        int debugMax = Integer.MIN_VALUE;
        for (int n = 0; n < 1 << numOfAnts; n++) {
            Stick stick = new Stick(length);
            traceList[n] = new ArrayList[numOfAnts];
            ArrayList<Ant> antsList = stick.getAnts();
            for (int i = 0; i < numOfAnts; i++) {
                traceList[n][i] = new ArrayList<>();
                Ant ant = new Ant(positions[i], (n >> i & 1) == 0 ? -1 : 1, speed);
                antsList.add(ant);
            }
            int time = 0;
            HashMap<Integer, Ant> occupied = stick.getOccupied();
            while (!stick.allAntsArrive()) {
                for (int i = 0; i < antsList.size(); i ++) {
                    Ant currentAnt = antsList.get(i);
                    if(!currentAnt.isArrived()){
                        occupied.remove(currentAnt.getCurrentPoint());
                        currentAnt.move();
                        if(stick.reach(currentAnt)){
                            currentAnt.arrive();
                            continue;
                        }
                        handleCollision(occupied, currentAnt);
                        occupied.put(currentAnt.getCurrentPoint(), currentAnt);
                        traceList[n][i].add(new PositionInfo(currentAnt.getCurrentPoint(), currentAnt.getDirection(), time));
                    }
                }
                time ++;
            }
            if(time == min * 2)
                minState = n;
            if(time == max * 2)
                maxState = n;
//            debugMin = Math.min(debugMin, time);
//            debugMax = Math.max(debugMax, time);
        }
//        System.out.println("debugMin = " + debugMin);
//        System.out.println("debugMax = " + debugMax);
//        System.out.println("min = " + min);
//        System.out.println("max = " + max);
    }

    public int getMinimumTime(){
        return min;
    }
    public int getMaximumTime(){
        return max;
    }
    public ArrayList[][] getTrace() {
        return traceList;
    }

    public int getMinState(){
        return minState;
    }

    public int getMaxState(){
        return maxState;
    }
//    public void printTrace(){
//        System.out.println("minState = " + minState);
//        System.out.println("maxState = " + maxState);
//        for (int i = 0; i < 1 << 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.println("i = " + i + " ant " + j + " " +
//                        traceList[i][j].stream().map(PositionInfo::toString).collect(Collectors.joining(" ")));
//            }
//        }
//    }
    private void handleCollision(Map<Integer, Ant> occupied, Ant currentAnt){
        if(occupied.containsKey(currentAnt.getCurrentPoint())){
            Ant conflictAnt = occupied.get(currentAnt.getCurrentPoint());
            if(conflictAnt.getDirection() ==  -1 * currentAnt.getDirection()){
                conflictAnt.changeDirection();
                currentAnt.changeDirection();
            }
        }
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        int [] positions = {3, 7, 11, 17, 23};
        board.run(positions, 1, 27);
//        board.printTrace();
    }
}
