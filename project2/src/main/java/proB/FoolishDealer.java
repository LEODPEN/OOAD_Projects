package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:02 下午
 */
public class FoolishDealer extends Dealer {
    private int currentPoint;

    public FoolishDealer(int money) {
        super(money);
    }

    @Override
    public boolean decideDraw() {
        return currentPoint <= 17;
    }
    public int getPoints(){
        return currentPoint;
    }
}
