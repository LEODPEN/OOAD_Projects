package proC.lyq;

/**
 * @author onion
 * @date 2019/11/20 -1:47 下午
 */
public class Circle implements Component{
    private Point point;
    private int scale;
    private int id;

    public Circle(Point point, int id) {
        this.point = point;
        this.scale = 1;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    public void incScale() {
        this.scale ++;
    }

    @Override
    public int getScale() {
        return scale;
    }
}
