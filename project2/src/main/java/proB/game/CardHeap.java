package proB.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author onion
 * @date 2019/10/21 -1:19 下午
 */
public class CardHeap {
    private ArrayList<Card> card;
    private Iterator it;
    private Integer size;
    private CardHeap(){
        card = new ArrayList<>();
        size = 52;
        for (int i = 1; i <= 13; i++){
            card.add(new Card(i,CardCategoryEnum.SPADE));
            card.add(new Card(i,CardCategoryEnum.HEART));
            card.add(new Card(i,CardCategoryEnum.CLUB));
            card.add(new Card(i,CardCategoryEnum.DIAMOND));
        }
    }
    private static CardHeap instance = new CardHeap();
    public static CardHeap getInstance(){
        return instance;
    }
    public Card nextCard(){
        if (!hasNext())
            throw new IllegalArgumentException("已经没有牌了");
        size --;
        return (Card) it.next();
    }
    public boolean hasNext(){
        return it.hasNext();
    }
    public void shuffle(){ // 打乱顺序与重开游戏
        Collections.shuffle(card);
        this.it = card.iterator();
        this.size=52;
    }
    public Integer getSize() {
        return size;
    }
}
