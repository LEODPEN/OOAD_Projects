package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:20 下午
 */
public class Calculator {
    public static int value(int value){
        return Math.min(value, 10);
    }
    public static int calculate(ArrayList<Card> cards){
        int sum = 0;
        for (Card card : cards) {
            sum+=value(card.getValue());
            if (value(card.getValue()) == 1 && sum <= 10)
                sum += 10;
        }
        return sum;
    }
    public static boolean isBlackJack(ArrayList<Card> cards){
        if (cards.size() != 2)
            return false;
        return  (cards.get(0).getValue().equals(1) && Calculator.value(cards.get(1).getValue())==10 ||
                cards.get(1).getValue().equals(1) && Calculator.value(cards.get(0).getValue())==10);
    }
}
