package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:38 下午
 * 关于决策是否抽牌的逻辑由子类实现
 */
public abstract class Dealer {
    private int money;
    private int currentValue;
    private ArrayList<Integer> currentCard;
    public abstract boolean decideDraw();
    public Dealer(int money){
        this.money = money;
        this.currentCard = new ArrayList<>();
    }
    //获取信息
    public ArrayList<Integer> getCurrentCard(){
        return currentCard;
    }
    public int getMoney(){
        return money;
    }
    public int getCurrentValue(){
        return currentValue;
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
    }
    //数据归零
    public void nextTurn(){
        currentCard.clear();
        currentValue = 0;
    }
}
