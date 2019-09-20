package proA.game;

import java.util.Arrays;

public class Options {
    private int num; // 蚂蚁只数

    private int speed; // 蚂蚁速度，为了可能后面是不同蚂蚁不同速度而设计

//    private String direction; // 蚂蚁初始方向，eg: '1 -1 1 1 -1 '(5 只蚂蚁的情况)

    private int length; // 杆长，貌似已经被default.

    private int[] beginPoint; // 蚂蚁初始位置， 貌似已经被default。

    public Options() {}

    public Options(int num, int speed, int length, int[] beginPoint) {
        this.num = num;
        this.speed = speed;
        this.length = length;
        this.beginPoint = beginPoint;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[] getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(int[] beginPoint) {
        this.beginPoint = beginPoint;
    }

    @Override
    public String toString() {
        return "Options{" +
                "num=" + num +
                ", speed=" + speed +
                ", length=" + length +
                ", beginPoint=" + Arrays.toString(beginPoint) +
                '}';
    }

}
