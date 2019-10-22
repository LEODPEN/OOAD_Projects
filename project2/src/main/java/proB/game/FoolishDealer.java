package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:02 下午
 */
public class FoolishDealer extends Dealer {
    public FoolishDealer(int money) {
        super(money);
    }
    @Override
    public boolean decideDraw() {
        return super.getCurrentValue() <= 17;
    }
}
