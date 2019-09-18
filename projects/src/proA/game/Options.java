package proA.game;

public class Options {
    private String num; // 蚂蚁只数

    private String speed; // 蚂蚁数量，为了可能后面是不同蚂蚁不同速度而设计

    private String direction; // 蚂蚁初始方向，eg: '1 -1 1 1 -1 '(5 只蚂蚁的情况)

    private String length; // 杆长，貌似已经被default.

    private String beginPoint; // 蚂蚁初始位置， 貌似已经被default。

    public Options() {}

    public Options(String num, String speed, String direction, String length, String beginPoint) {
        this.num = num;
        this.speed = speed;
        this.direction = direction;
        this.length = length;
        this.beginPoint = beginPoint;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(String beginPoint) {
        this.beginPoint = beginPoint;
    }

    @Override
    public String toString() {
        return " This option is {" +
                "num='" + num + '\'' +
                ", speed='" + speed + '\'' +
                ", direction='" + direction + '\'' +
                ", length='" + length + '\'' +
                ", beginPoint='" + beginPoint + '\'' +
                '}';
    }
}
