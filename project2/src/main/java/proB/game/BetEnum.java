package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:16 下午
 */
public enum BetEnum {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    ;
    private int bet;
    public int getBet(){
        return bet;
    }
    BetEnum(int bet){
        this.bet = bet;
    }
}
