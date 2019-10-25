package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:20 下午
 */
public class Calculator {
    public static int value(int value){
        // get the true value
        // k 0
        // a - 10 1-10
        // 11 - 12 J Q
        return Math.min(value, 10);
    }

    public static int calculate(ArrayList<Card> cards){
        int sum = 0;
        for (Card card : cards) {
            sum+=value(card.getValue());
//            sum += value(card.getValue());
//            if (value(point) == 1 && sum <= 10)
//                sum += 10;
        }
        return sum;
    }
    public static boolean isBlackJack(ArrayList<Card> cards){
        if (cards.size() != 2)
            return false;

        return  (cards.get(0).getValue().equals(1) && cards.get(1).getValue().equals(10) ||
                cards.get(1).getValue().equals(1) && cards.get(0).getValue().equals(10));

//        return value(points.get(1)) == 1 && value(points.get(0)) == 10
//                || value(points.get(0)) == 1 && value(points.get(1)) == 10;
    }
}
