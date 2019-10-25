package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -1:43 下午
 * 用于操作player 和 dealer的类。
 */

// 当前只有人机模式
public class PlayBoard {
    private Player player;
    private Dealer dealer;
    private int bet;
    private int betMoney;
    private CardHeap cardHeap;

    // 刚开始玩家有100，50，20，10
    public PlayBoard(int money, int level){
        cardHeap = CardHeap.getInstance();
        player = new Player(money); //初始化起始金币数量
        if (level == 1) //选择Dealer的智商
            dealer = new FoolishDealer();
        else
            dealer = new SmartDealer();
    }

    public PlayBoard(int level){
        cardHeap = CardHeap.getInstance();
        player = new Player(180); //初始化起始金币数量
        if (level == 1) //选择Dealer的智商
            dealer = new FoolishDealer();
        else
            dealer = new SmartDealer();
    }

    //1. 下注
    public void bet(BetEnum betNum){
        this.bet = betNum.getBet();
        player.betMoney(bet);
//        dealer.betMoney(bet);
        betMoney = 2 * bet;
    }
    //2. 初始2张手牌
    public void initialDraw(){
        for (int i = 0; i < 2; i++) {
            dealer.hit(cardHeap.nextCard());
            player.hit(cardHeap.nextCard());
        }
    }
    //3. 判断是否有BlackJack
    public WinningState judgeBlackJack(){
        boolean playerBlackJack = Calculator.isBlackJack(player.getCurrentCard());
        boolean dealerBlackJack = Calculator.isBlackJack(dealer.getCurrentCard());
        if (playerBlackJack && ! dealerBlackJack) {
            player.earnMoney((int) (1.5 * betMoney));
            return WinningState.PLAYER_JACK;
        }
        if (! playerBlackJack && dealerBlackJack) {
//            dealer.earnMoney((int) (1.5 * betMoney));
            return WinningState.DEALER_JACK;
        }
        // 同时 blackjack
        if (playerBlackJack)
            return WinningState.DRAW;
        return WinningState.NOT_DECIDE;
    }
    //4. 选择是否加倍（仅1次）
    public void doubleBet(){
        player.betMoney(bet);
        betMoney *= 2;
    }
    //5. 玩家抽牌
    public void playerDrawCard(){
        if (cardHeap.hasNext())
            player.hit(cardHeap.nextCard());
    }
    //6. 庄家抽牌
    public void dealerDrawCard(){
        while (dealer.decideDraw())
            if (cardHeap.hasNext())
                dealer.hit(cardHeap.nextCard());
    }
    //7. 判断结果
    public WinningState decideWinner(){
        if (player.getCurrentValue() > 21 || player.getCurrentValue() < dealer.getCurrentValue()) {
//            dealer.earnMoney(betMoney);
            return WinningState.DEALER_WIN;
        }
        if (dealer.getCurrentValue() > 21 || dealer.getCurrentValue() < player.getCurrentValue()) {
            player.earnMoney(betMoney);
            return WinningState.PLAYER_WIN;
        }
        return WinningState.DRAW;
    }
    //8. 进行下一场比赛
    public void nextGame(){
        cardHeap.shuffle();
        player.nextTurn();
        dealer.nextTurn();
        if (decideWinner() != WinningState.DRAW)
            betMoney = 0;
    }
    //9. 接口
    public CardHeap getCardHeap() {
        return cardHeap;
    }
    public Player getPlayer(){
        return player;
    }
    public Dealer getDealer(){
        return dealer;
    }
}
