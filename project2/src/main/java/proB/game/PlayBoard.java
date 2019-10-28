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
    private WinningState winningState;

    // 刚开始玩家有100，50，20，10
    public PlayBoard(int level){
        betMoney=0;
        cardHeap = CardHeap.getInstance();
        player = new Player(180); //初始化起始金币数量
        if (level == 1) //选择Dealer的智商
            dealer = new FoolishDealer();
        else
            dealer = new SmartDealer();
    }

    public void changeDealerLevel(int level){
        if (dealer instanceof FoolishDealer && level == 2){
            dealer = new SmartDealer();
            System.out.println("now smartDealer");
        }else if (dealer instanceof SmartDealer && level == 1){
            dealer = new FoolishDealer();
            System.out.println("now foolishDealer");
        }else{
            throw new IllegalArgumentException("fk ! do not change level over and over again!");
        }

    }

    //1. 下注，可以连续调用
    public void bet(BetEnum betNum){
        this.bet = betNum.getBet();
        player.betMoney(bet);
        betMoney += 2 * bet;
    }
    //2. 初始2张手牌
    public void initialDraw(){
        for (int i = 0; i < 2; i++) {
            dealer.hit(cardHeap.nextCard());
            player.hit(cardHeap.nextCard());
        }
    }
    //3. 判断是否有BlackJack
    public void judgeBlackJack(){
        boolean playerBlackJack = Calculator.isBlackJack(player.getCurrentCard());
        boolean dealerBlackJack = Calculator.isBlackJack(dealer.getCurrentCard());
        if (playerBlackJack && ! dealerBlackJack) {
            player.earnMoney((int) (1.5 * betMoney));
            winningState = WinningState.PLAYER_JACK;
            return;
        }
        if (! playerBlackJack && dealerBlackJack) {
            winningState = WinningState.DEALER_JACK;
            return;
        }
        if (playerBlackJack){
            winningState = WinningState.DRAW;
            return;
        }
        winningState = WinningState.NOT_DECIDE;
    }
    public WinningState getWinningState(){
        return winningState;
    }
    //4. 选择是否加倍（仅1次）
    public void doubleBet(){
        player.betMoney(betMoney/2);
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
    public void decideWinner(){
        if (winningState != WinningState.NOT_DECIDE)
            return;
        if (player.getCurrentValue() < dealer.getCurrentValue() && dealer.getCurrentValue() <= 21)
            winningState = WinningState.DEALER_WIN;
        if (dealer.getCurrentValue() < player.getCurrentValue() && player.getCurrentValue() <= 21)
            winningState = WinningState.PLAYER_WIN;
        if (dealer.getCurrentValue() == player.getCurrentValue() && player.getCurrentValue() <= 21)
            winningState = WinningState.DRAW;
    }
    public void settleMoney(){
        if (winningState == WinningState.PLAYER_WIN)
            player.earnMoney(betMoney);
        if (winningState == WinningState.PLAYER_JACK)
            player.earnMoney((int) (1.5*betMoney));
        if (winningState == WinningState.DRAW)
            player.earnMoney(betMoney/2);
    }
    public void setWinningState(WinningState state){
        this.winningState = state;
    }
    //8. 进行一场新比赛,重置金钱
    public void nextGame(){

        cardHeap.shuffle();
        player.nextTurn();
        dealer.nextTurn();

        betMoney=0;
    }

    //下一局比赛,保留该局金钱
    public void nextTurn(){
        player.nextTurnWithMoney();
        dealer.nextTurn();
        betMoney=0;
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

    public int getBetMoney() {
        return betMoney;
    }

}
