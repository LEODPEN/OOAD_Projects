package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:34 下午
 */
public class Player {
    private int money;
    private ArrayList<Integer> currentCard;
    private int currentValue;
    public Player(int money){
        this.money = money;
        currentCard = new ArrayList<>();
    }

    //算钱
    public void betMoney(int bet){
        if (money - bet < 0)
            throw new IllegalArgumentException("赌金不足！");
        money -= bet;
    }

    public void earnMoney(int bet){
        money += bet;
    }
    //抽牌
    public void hit(int card){
        currentCard.add(card);
        currentValue = Calculator.calculate(currentCard);
    }
    //获取信息
    public ArrayList<Integer> getCurrentCard(){
        return currentCard;
    }
    public int getCurrentValue(){
        return currentValue;
    }
    public int getMoney(){
        return money;
    }
    //数据归零
    public void nextTurn(){
        currentCard.clear();
        currentValue = 0;
    }


}
