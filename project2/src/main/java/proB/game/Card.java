package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -1:19 下午
 */
public class Card {
    private int [] card;
    private int cursor = 0;
    private Card(){
        card = new int[52];
        for (int i = 0; i < 52; i++)
            card[i] = i;
    }
    private static Card instance = new Card();
    public static Card getInstance(){
        return instance;
    }
    private void swap(int x, int y){
        int temp = card[x];
        card[x] = card[y];
        card[y] = temp;
    }
    public int nextCard(){
        if (cursor == 52)
            throw new IllegalArgumentException("已经没有牌了");
        return card[cursor++];
    }
    public boolean hasNext(){
        return cursor < 52;
    }
    public void shuffle(){
        cursor = 0;
        for (int i = 51; i >= 0; i--){
            int randomNumber = (int)(Math.random()*(i+1));
            swap(i, randomNumber);
        }
    }
}
