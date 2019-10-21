package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -1:56 下午
 */
public class SmartDealer extends Dealer {
    private int currentPoint;

    public SmartDealer(int money) {
        super(money);
    }

    @Override
    public boolean decideDraw() {
        return false;
    }
    public int getPoints(){
        return currentPoint;
    }
}
