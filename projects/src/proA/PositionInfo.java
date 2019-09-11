package sample.game;

/**
 * @author onion
 * @date 2019/9/11 -12:13 下午
 */
public class PositionInfo {
    private int currentPosition;
    private int direction;
    private int time;
    public PositionInfo(int currentPosition, int direction, int time) {
        this.currentPosition = currentPosition;
        this.direction = direction;
        this.time = time;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public int getDirection() {
        return direction;
    }
    @Override
    public String toString(){
        return time + "s:" + currentPosition;
    }
}
