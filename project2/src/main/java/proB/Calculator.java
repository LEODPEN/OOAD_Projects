package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:20 下午
 */
public class Calculator {
    private static int value(int point){
        // k 0
        // a - 10 1-10
        // 11 - 12 J Q
        if (point % 13 >= 1 && point % 13 <= 10)
            return point % 13;
        else
            return 10;
    }
    public static int calculate(ArrayList<Integer> points){
        int sum = 0;
        for (Integer point : points) {
            sum += value(point);
            if (value(point) == 1 && sum <= 10)
                sum += 10;
        }
        return sum;
    }
    public static boolean isBlackJack(ArrayList<Integer> points){
        if (points.size() != 2)
            return false;
        return value(points.get(1)) == 1 && value(points.get(0)) == 10
                || value(points.get(0)) == 1 && value(points.get(1)) == 10;
    }
}
