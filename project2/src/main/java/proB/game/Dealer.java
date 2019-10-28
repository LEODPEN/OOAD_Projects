package proB.game;

import java.util.ArrayList;

/**
 * @author onion
 * @date 2019/10/21 -1:38 下午
 * 关于决策是否抽牌的逻辑由子类实现
 */
public abstract class Dealer {
    private int currentValue;
    private ArrayList<Card> currentCards;
    public abstract boolean decideDraw();
    public Dealer(){
        this.currentCards = new ArrayList<>();
    }
    //获取信息
    public ArrayList<Card> getCurrentCard(){
        return currentCards;
    }
    public int getCurrentValue(){
        return currentValue;
    }
    //抽牌
    public void hit(Card card){
        currentCards.add(card);
        currentValue = Calculator.calculate(currentCards);
    }
    //数据归零
    public void nextTurn(){
        currentCards.clear();
    }
}
