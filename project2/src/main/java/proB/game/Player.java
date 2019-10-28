package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:34 下午
 */
public class Player {
    private int money;
    private ArrayList<Card> currentCards;
    private int currentValue;
    public Player(int money){
        this.money = money;
        currentCards = new ArrayList<>();
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
    public void hit(Card card){
        currentCards.add(card);
        currentValue = Calculator.calculate(currentCards);
    }
    //获取信息
    public ArrayList<Card> getCurrentCard(){
        return currentCards;
    }
    public int getCurrentValue(){
        return currentValue;
    }
    public int getMoney(){
        return money;
    }
    //数据归零，重新开始游戏，重置money
    public void nextTurn(){
        currentCards.clear();
        currentValue = 0;
        money = 180;
    }

    //清除上一局手牌数据，本轮money不变，继续游戏
    public void nextTurnWithMoney(){
        currentCards.clear();
        currentValue = 0;
    }


}
