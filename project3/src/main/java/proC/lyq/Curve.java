package proC.lyq;

/**
 * @author onion
 * @date 2019/11/20 -1:50 下午
 */
public class Curve implements Component{
    private int direction;
    private int id;
    private Point point;
    private int scale;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void incScale() {
        scale ++;
    }

    @Override
    public int getScale() {
        return scale;
    }
}
