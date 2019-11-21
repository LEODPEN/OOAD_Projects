package proC.lyq;

/**
 * @author onion
 * @date 2019/11/20 -1:52 下午
 */
public class Handler {
    public void enlarge(Board board, Component component){
        int id = component.getId();
        Point point = component.getPoint();
        int x = point.getX();
        int y = point.getY();
        int[][] used = board.getUsed();
        if (point.getX() == 19 || point.getY() == 19)
            return;

        used[x+1][y] = id;
        used[x][y+1] = id;
        used[x+1][y+1] = id;
        component.incScale();
    }
    public void shrink(Board board, Component component){
        int scale = component.getScale();
        if (scale == 1)
            return;
        int id = component.getId();
        Point point = component.getPoint();
        int x = point.getX();
        int y = point.getY();
        int[][] used = board.getUsed();
        used[x+scale-1][y] = -1;
        used[x+scale-1][y+scale-1] = -1;
        used[x+scale-1][y+1] = -1;
        component.incScale();
    }

}
