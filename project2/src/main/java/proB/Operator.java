package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -1:43 下午
 */
public class Operator {
    private Player player;
    private Dealer dealer;
    private int bet;
    private int betMoney;
    private Card card;
    private boolean playerBlackJack;
    private boolean dealerBlackJack;
    public Operator(int money, int level){
        card = Card.getInstance();
        player = new Player(money);
        if (level == 1)
            dealer = new FoolishDealer(money);
        else
            dealer = new SmartDealer(money);
    }
    //1. 下注
    public void bet(int bet){
        this.bet = bet;
        player.betMoney(bet);
        dealer.betMoney(bet);
        betMoney = 2 * bet;
    }
    //2. 初始2张手牌
    public void initialDraw(){
        for (int i = 0; i < 2; i++) {
            dealer.hit(card.nextCard());
            player.hit(card.nextCard());
        }
    }
    //3. 判断是否有BlackJack
    public void judgeBlackJack(){
        playerBlackJack = Calculator.isBlackJack(player.getCurrentCard());
        dealerBlackJack = Calculator.isBlackJack(dealer.getCurrentCard());
    }
    //4. 选择是否加倍（仅1次）
    public void doubleBet(){
        player.betMoney(bet);
        betMoney *= 2;
    }
    //5. 玩家抽牌
    public void playerDrawCard(){
        if (card.hasNext())
            player.hit(card.nextCard());
    }
    //6. 庄家抽牌
    public void dealerDrawCard(){
        while (dealer.decideDraw()){
            if (card.hasNext())
                dealer.hit(card.nextCard());
        }
    }
    //7. 判断结果
    public WinningState decideWinner(){
        if (player.getCurrentValue() > 21 || player.getCurrentValue() < dealer.getCurrentValue()) {
            dealer.earnMoney(betMoney);
            return WinningState.DEALER_WIN;
        }
        if (dealer.getCurrentValue() > 21 || dealer.getCurrentValue() < player.getCurrentValue()) {
            player.earnMoney(betMoney);
            return WinningState.PLAYER_WIN;
        }
        if (playerBlackJack && !dealerBlackJack) {
            player.earnMoney((int) (1.5 * betMoney));
            return WinningState.PLAYER_JACK;
        }
        if (!playerBlackJack && dealerBlackJack) {
            dealer.earnMoney((int) (1.5 * betMoney));
            return WinningState.DEALER_JACK;
        }
        return WinningState.DRAW;
    }
    //8. 进行下一场比赛
    public void nextGame(){
        card.shuffle();
        player.nextTurn();
        dealer.nextTurn();
        if (decideWinner() != WinningState.DRAW)
            betMoney = 0;
    }
}
