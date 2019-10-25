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
//    private int cursor = 0;
    private CardHeap(){
        // initialize the whole card heap.
        card = new ArrayList<>();
        for (int i = 1; i <= 13; i++){
            card.add(new Card(i,CardCategoryEnum.HEI_TAO));
            card.add(new Card(i,CardCategoryEnum.HONG_TAO));
            card.add(new Card(i,CardCategoryEnum.MEI_HUA));
            card.add(new Card(i,CardCategoryEnum.FANG_KUAI));
        }
        // 现在是没有打乱顺序的
    }
    private static CardHeap instance = new CardHeap();
    public static CardHeap getInstance(){
        return instance;
    }
//    private void swap(int x, int y){
//        int temp = card[x];
//        card[x] = card[y];
//        card[y] = temp;
//    }
//    public int nextCard(){
//        if (cursor == 52)
//            throw new IllegalArgumentException("已经没有牌了");
//        return card[cursor++];
//    }

    public Card nextCard(){
        if (!hasNext())
            throw new IllegalArgumentException("已经没有牌了");
        return (Card) it.next();
    }

    public boolean hasNext(){
        return it.hasNext();
    }
//    public void shuffle(){
//        cursor = 0;
//        for (int i = 51; i >= 0; i--){
//            int randomNumber = (int)(Math.random()*(i+1));
//            swap(i, randomNumber);
//        }
//    }
    public void shuffle(){ // 打乱顺序与重开游戏
        Collections.shuffle(card);
        this.it = card.iterator();
    }
}
