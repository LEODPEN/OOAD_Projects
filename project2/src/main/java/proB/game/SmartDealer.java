package proB.game;

import java.util.Random;

/**
 * @author onion
 * @date 2019/10/21 -1:56 下午
 */
public class SmartDealer extends Dealer {
    private Random random;
    public SmartDealer() {
        random = new Random();
    }
    @Override
    public boolean decideDraw() {
        int tryHit = random.nextInt(13);
        return super.getCurrentValue() + Calculator.value(tryHit) <= 21;
    }

}
