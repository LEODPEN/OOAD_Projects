package proC.lyq;

import proC.physicsWorld.GeometryImpl;

import java.util.Arrays;
import java.util.Map;

/**
 * @author onion
 * @date 2019/11/20 -1:47 下午
 */
public class Board {
    private int [][] used = new int[20][20];
    private Map<Integer, Component> map;
    private GeometryImpl impl = new GeometryImpl();

    public Board(){
        for (int i = 0; i < used.length; i++) {
            Arrays.fill(used[i], -1);
        }
    }
    public int [][] getUsed(){
        return used;
    }
    public void test(){

    }
}
