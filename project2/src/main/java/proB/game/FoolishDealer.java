package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:02 下午
 */
public class FoolishDealer extends Dealer {

    @Override
    public boolean decideDraw() {
        return super.getCurrentValue() <= 17;
    }
}
